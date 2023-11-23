/*
 * Copyright 2016 John Grosh <john.a.grosh@gmail.com>.
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
package com.jagrosh.jmusicbot.commands.owner;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.OwnerCommand;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

public class OverviewCmd extends OwnerCommand
{
    private final Bot bot;
    public OverviewCmd(Bot bot)
    {
        this.bot = bot;
        this.guildOnly = false;
        this.name = "overview";
        this.help = "provides an overview of what is currently being played by the bot";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    public void execute(CommandEvent event) 
    {
        StringBuilder playing = new StringBuilder();
        StringBuilder empty = new StringBuilder();
        playing.append("Currently playing:\n");
        empty.append("Currently inactive:\n");
        for (Guild g: bot.getJDA().getGuilds()) {
            AudioHandler handler = (AudioHandler)g.getAudioManager().getSendingHandler();
            if (handler != null) {
                AudioTrack track = handler.getPlayer().getPlayingTrack();
                playing.append("- ")
                        .append(g.getName())
                        .append(" [")
                        .append(g.getId())
                        .append("]: ")
                        .append(track.getInfo().title)
                        .append("\n");
            } else {
                empty.append("- ")
                        .append(g.getName())
                        .append(" [")
                        .append(g.getId())
                        .append("]\n");
            }
        }
        playing.append(empty);
        event.reply(playing.toString());
    }
}
