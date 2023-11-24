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

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.examples.command.PingCommand;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.commands.OwnerCommand;
import com.jagrosh.jmusicbot.commands.admin.*;
import com.jagrosh.jmusicbot.commands.dj.*;
import com.jagrosh.jmusicbot.commands.general.SettingsCmd;
import com.jagrosh.jmusicbot.commands.music.*;
import com.jagrosh.jmusicbot.utils.CustomMessage;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ServerCmd extends OwnerCommand {
    private final Bot bot;
    public final static Logger LOG = LoggerFactory.getLogger(ServerCmd.class);

    public ServerCmd(Bot bot) {
        this.bot = bot;
        this.guildOnly = false;
        this.name = "server";
        this.arguments = "[guild] [command]";
        this.help = "override any other command with full permissions in a specific guild";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    public void execute(CommandEvent event) {
        String[] parts = event.getArgs().split("\\s+", 3);
        if (parts.length < 2)  {
            return;
        }

        List<Command> commands = new ArrayList<>();
        commands.add(new PingCommand());
        commands.add(new SettingsCmd(bot));
        commands.add(new LyricsCmd(bot));
        commands.add(new NowplayingCmd(bot));
        commands.add(new PlayCmd(bot));
        commands.add(new PlaylistsCmd(bot));
        commands.add(new QueueCmd(bot));
        commands.add(new RemoveCmd(bot));
        commands.add(new SearchCmd(bot));
        commands.add(new SCSearchCmd(bot));
        commands.add(new ShuffleCmd(bot));
        commands.add(new SkipCmd(bot));
        commands.add(new ForceRemoveCmd(bot));
        commands.add(new ForceskipCmd(bot));
        commands.add(new MoveTrackCmd(bot));
        commands.add(new PauseCmd(bot));
        commands.add(new PlaynextCmd(bot));
        commands.add(new RepeatCmd(bot));
        commands.add(new SkiptoCmd(bot));
        commands.add(new StopCmd(bot));
        commands.add(new VolumeCmd(bot));
        commands.add(new PrefixCmd(bot));
        commands.add(new SetdjCmd(bot));
        commands.add(new SkipratioCmd(bot));
        commands.add(new SettcCmd(bot));
        commands.add(new SetvcCmd(bot));
        commands.add(new PlaylistCmd(bot));

        Guild guild = bot.getJDA().getGuildById(parts[0]);
        TextChannel channel = bot.getJDA().getGuildById("771875907811409920").getDefaultChannel();
        String command_name = parts[1];
        String extra = "";
        if (parts.length >= 3) {
            extra = parts[2];
        }
        LOG.info("Sending command {} with extra {} to guild {}", command_name, extra, guild.getName());
        for (Command command: commands) {
            if (command.getName().equals(command_name) || Arrays.asList(command.getAliases()).contains(command_name)) {
                CustomMessage customMessage = new CustomMessage(event.getMessage(), guild, channel);
                MessageReceivedEvent newMessageReceivedEvent = new MessageReceivedEvent(event.getJDA(), event.getResponseNumber(), customMessage);
                CommandEvent newCommandEvent = new CommandEvent(newMessageReceivedEvent, extra, event.getClient());
                command.run(newCommandEvent);
                break;
            }
        }
    }
};
