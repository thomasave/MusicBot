package com.jagrosh.jmusicbot.utils;

import com.jagrosh.jmusicbot.commands.owner.ServerCmd;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ComponentLayout;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;
import org.apache.commons.collections4.Bag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.*;

public class CustomMessage implements Message {
    private final Guild myGuild;
    private Message original;
    private TextChannel channel;
    private Member member;

    public final static Logger LOG = LoggerFactory.getLogger(CustomMessage.class);

    public CustomMessage(Message message, Guild guild, TextChannel messageChannel) {
        myGuild = guild;
        original = message;
        channel = messageChannel;
        Member author = guild.getMemberById(message.getAuthor().getId());
        if (author != null) {
             GuildVoiceState state = author.getVoiceState();
             if (state != null && state.inVoiceChannel()) {
                 member = author;
             }
        }
        if (member == null) {
            for (VoiceChannel c: guild.getVoiceChannels()) {
                for (Member m: c.getMembers()) {
                    member = m;
                    LOG.info("Selected member: {}", m.getEffectiveName());
                    break;
                }
            }
        }
        if (member == null) {
            LOG.error("Could not find a suitable member in a voice channel on server {}", guild.getName());
        }
    }

    @Nullable
    @Override
    public MessageReference getMessageReference() {
        return original.getMessageReference();
    }

    @NotNull
    @Override
    public List<User> getMentionedUsers() {
        return original.getMentionedUsers();
    }

    @NotNull
    @Override
    public Bag<User> getMentionedUsersBag() {
        return original.getMentionedUsersBag();
    }

    @NotNull
    @Override
    public List<TextChannel> getMentionedChannels() {
        List<TextChannel> list = new ArrayList<>();
        list.add(channel);
        return list;
    }

    @NotNull
    @Override
    public Bag<TextChannel> getMentionedChannelsBag() {
        return original.getMentionedChannelsBag();
    }

    @NotNull
    @Override
    public List<Role> getMentionedRoles() {
        return original.getMentionedRoles();
    }

    @NotNull
    @Override
    public Bag<Role> getMentionedRolesBag() {
        return original.getMentionedRolesBag();
    }

    @NotNull
    @Override
    public List<Member> getMentionedMembers(@NotNull Guild guild) {
        return original.getMentionedMembers(guild);
    }

    @NotNull
    @Override
    public List<Member> getMentionedMembers() {
        return original.getMentionedMembers();
    }

    @NotNull
    @Override
    public List<IMentionable> getMentions(@NotNull MentionType... mentionTypes) {
        return original.getMentions(mentionTypes);
    }

    @Override
    public boolean isMentioned(@NotNull IMentionable iMentionable, @NotNull MentionType... mentionTypes) {
        return original.isMentioned(iMentionable, mentionTypes);
    }

    @Override
    public boolean mentionsEveryone() {
        return original.mentionsEveryone();
    }

    @Override
    public boolean isEdited() {
        return original.isEdited();
    }

    @Nullable
    @Override
    public OffsetDateTime getTimeEdited() {
        return original.getTimeEdited();
    }

    @NotNull
    @Override
    public User getAuthor() {
        return original.getAuthor();
    }

    @Nullable
    @Override
    public Member getMember() {
        return member;
    }

    @NotNull
    @Override
    public String getJumpUrl() {
        return original.getJumpUrl();
    }

    @NotNull
    @Override
    public String getContentDisplay() {
        return original.getContentDisplay();
    }

    @NotNull
    @Override
    public String getContentRaw() {
        return original.getContentRaw();
    }

    @NotNull
    @Override
    public String getContentStripped() {
        return original.getContentStripped();
    }

    @NotNull
    @Override
    public List<String> getInvites() {
        return original.getInvites();
    }

    @Nullable
    @Override
    public String getNonce() {
        return original.getNonce();
    }

    @Override
    public boolean isFromType(@NotNull ChannelType channelType) {
        return channel.getType() == channelType;
    }

    @NotNull
    @Override
    public ChannelType getChannelType() {
        return channel.getType();
    }

    @Override
    public boolean isWebhookMessage() {
        return original.isWebhookMessage();
    }

    @NotNull
    @Override
    public MessageChannel getChannel() {
        return channel;
    }

    @NotNull
    @Override
    public PrivateChannel getPrivateChannel() {
        return original.getPrivateChannel();
    }

    @NotNull
    @Override
    public TextChannel getTextChannel() {
        return channel;
    }

    @Nullable
    @Override
    public Category getCategory() {
        return original.getCategory();
    }

    public @NotNull Guild getGuild() {
        return myGuild;
    }

    @NotNull
    @Override
    public List<Attachment> getAttachments() {
        return original.getAttachments();
    }

    @NotNull
    @Override
    public List<MessageEmbed> getEmbeds() {
        return original.getEmbeds();
    }

    @NotNull
    @Override
    public List<ActionRow> getActionRows() {
        return original.getActionRows();
    }

    @NotNull
    @Override
    public List<Emote> getEmotes() {
        return original.getEmotes();
    }

    @NotNull
    @Override
    public Bag<Emote> getEmotesBag() {
        return original.getEmotesBag();
    }

    @NotNull
    @Override
    public List<MessageReaction> getReactions() {
        return original.getReactions();
    }

    @NotNull
    @Override
    public List<MessageSticker> getStickers() {
        return original.getStickers();
    }

    @Override
    public boolean isTTS() {
        return original.isTTS();
    }

    @Nullable
    @Override
    public MessageActivity getActivity() {
        return original.getActivity();
    }

    @NotNull
    @Override
    public MessageAction editMessage(@NotNull CharSequence charSequence) {
        return original.editMessage(charSequence);
    }

    @NotNull
    @Override
    public MessageAction editMessageEmbeds(@NotNull Collection<? extends MessageEmbed> collection) {
        return original.editMessageEmbeds(collection);
    }

    @NotNull
    @Override
    public MessageAction editMessageComponents(@NotNull Collection<? extends ComponentLayout> collection) {
        return original.editMessageComponents(collection);
    }

    @NotNull
    @Override
    public MessageAction editMessageFormat(@NotNull String s, @NotNull Object... objects) {
        return original.editMessageFormat(s, objects);
    }

    @NotNull
    @Override
    public MessageAction editMessage(@NotNull Message message) {
        return original.editMessage(message);
    }

    @NotNull
    @Override
    public AuditableRestAction<Void> delete() {
        return original.delete();
    }

    @NotNull
    @Override
    public JDA getJDA() {
        return original.getJDA();
    }

    @Override
    public boolean isPinned() {
        return original.isPinned();
    }

    @NotNull
    @Override
    public RestAction<Void> pin() {
        return original.pin();
    }

    @NotNull
    @Override
    public RestAction<Void> unpin() {
        return original.unpin();
    }

    @NotNull
    @Override
    public RestAction<Void> addReaction(@NotNull Emote emote) {
        return original.addReaction(emote);
    }

    @NotNull
    @Override
    public RestAction<Void> addReaction(@NotNull String s) {
        return original.addReaction(s);
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactions() {
        return original.clearReactions();
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactions(@NotNull String s) {
        return original.clearReactions(s);
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactions(@NotNull Emote emote) {
        return original.clearReactions(emote);
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull Emote emote) {
        return original.removeReaction(emote);
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull Emote emote, @NotNull User user) {
        return original.removeReaction(emote, user);
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull String s) {
        return original.removeReaction(s);
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull String s, @NotNull User user) {
        return original.removeReaction(s, user);
    }

    @NotNull
    @Override
    public ReactionPaginationAction retrieveReactionUsers(@NotNull Emote emote) {
        return original.retrieveReactionUsers(emote);
    }

    @NotNull
    @Override
    public ReactionPaginationAction retrieveReactionUsers(@NotNull String s) {
        return original.retrieveReactionUsers(s);
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionByUnicode(@NotNull String s) {
        return original.getReactionByUnicode(s);
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionById(@NotNull String s) {
        return original.getReactionById(s);
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionById(long l) {
        return original.getReactionById(l);
    }

    @NotNull
    @Override
    public AuditableRestAction<Void> suppressEmbeds(boolean b) {
        return original.suppressEmbeds(b);
    }

    @NotNull
    @Override
    public RestAction<Message> crosspost() {
        return original.crosspost();
    }

    @Override
    public boolean isSuppressedEmbeds() {
        return original.isSuppressedEmbeds();
    }

    @NotNull
    @Override
    public EnumSet<MessageFlag> getFlags() {
        return original.getFlags();
    }

    @Override
    public long getFlagsRaw() {
        return original.getFlagsRaw();
    }

    @Override
    public boolean isEphemeral() {
        return original.isEphemeral();
    }

    @NotNull
    @Override
    public MessageType getType() {
        return original.getType();
    }

    @Nullable
    @Override
    public Interaction getInteraction() {
        return original.getInteraction();
    }

    @Override
    public void formatTo(Formatter formatter, int i, int i1, int i2) {
        original.formatTo(formatter, i, i1, i2);
    }

    @Override
    public long getIdLong() {
        return original.getIdLong();
    }
}
