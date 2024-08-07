/*
 * Copyright 2018 John Grosh <john.a.grosh@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.settings.Settings;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.exceptions.PermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author John Grosh <john.a.grosh@gmail.com>
 */
public abstract class MusicCommand extends Command
{
    protected final Bot bot;
    protected boolean bePlaying;
    protected boolean beListening;
    public final static Logger LOG = LoggerFactory.getLogger(MusicCommand.class);

    public MusicCommand(Bot bot)
    {
        this.bot = bot;
        this.guildOnly = true;
        this.category = new Category("Music");
    }

    @Override
    protected void execute(CommandEvent event)
    {
        Guild guild = event.getMessage().getMember().getGuild();
        Settings settings = event.getClient().getSettingsFor(guild);
        TextChannel tchannel = settings.getTextChannel(guild);
        LOG.info("Received a MusicCommand from " + event.getAuthor().getName() + " on channel " + event.getChannel().getName() + " from server " + guild.getName() + ": " + event.getMessage().getContentStripped());
        if(tchannel!=null && !event.getTextChannel().equals(tchannel))
        {
            try
            {
                event.getMessage().delete().queue();
            } catch(PermissionException ignore){}
            event.replyInDm(event.getClient().getError()+" You can only use that command in "+tchannel.getAsMention()+"!");
            return;
        }
        bot.getPlayerManager().setUpHandler(guild); // no point constantly checking for this later
        if(bePlaying && !((AudioHandler)guild.getAudioManager().getSendingHandler()).isMusicPlaying(event.getJDA()))
        {
            event.reply(event.getClient().getError()+" There must be music playing to use that!");
            return;
        }
        if(beListening)
        {
            VoiceChannel current = guild.getSelfMember().getVoiceState().getChannel();
            if(current==null)
                current = settings.getVoiceChannel(guild);
            GuildVoiceState userState = event.getMember().getVoiceState();
            if(!userState.inVoiceChannel() || userState.isDeafened() || (current!=null && !userState.getChannel().equals(current)))
            {
                event.replyError("You must be listening in "+(current==null ? "a voice channel" : current.getAsMention())+" to use that!");
                return;
            }

            VoiceChannel afkChannel = guild.getAfkChannel();
            if(afkChannel != null && afkChannel.equals(userState.getChannel()))
            {
                event.replyError("You cannot use that command in an AFK channel!");
                return;
            }

            if(!guild.getSelfMember().getVoiceState().inVoiceChannel())
            {
                try
                {
                    guild.getAudioManager().openAudioConnection(userState.getChannel());
                }
                catch(PermissionException ex)
                {
                    event.reply(event.getClient().getError()+" I am unable to connect to "+userState.getChannel().getAsMention()+"!");
                    return;
                }
            }
        }

        doCommand(event);
    }

    public abstract void doCommand(CommandEvent event);
}
