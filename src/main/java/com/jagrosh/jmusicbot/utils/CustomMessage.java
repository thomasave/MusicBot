package com.jagrosh.jmusicbot.utils;

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
import org.w3c.dom.Text;

import java.time.OffsetDateTime;
import java.util.*;

public class CustomMessage implements Message {
    private final Guild myGuild;
    private Message original;
    private TextChannel channel;
    private Member member;

    public CustomMessage(Message message, Guild guild, TextChannel messageChannel) {
        myGuild = guild;
        original = message;
        channel = messageChannel;
        member = guild.getMemberById(message.getAuthor().getId());
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
        return null;
    }

    @Override
    public boolean isMentioned(@NotNull IMentionable iMentionable, @NotNull MentionType... mentionTypes) {
        return false;
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
        return null;
    }

    @NotNull
    @Override
    public List<MessageSticker> getStickers() {
        return null;
    }

    @Override
    public boolean isTTS() {
        return false;
    }

    @Nullable
    @Override
    public MessageActivity getActivity() {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessage(@NotNull CharSequence charSequence) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessageEmbeds(@NotNull Collection<? extends MessageEmbed> collection) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessageComponents(@NotNull Collection<? extends ComponentLayout> collection) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessageFormat(@NotNull String s, @NotNull Object... objects) {
        return null;
    }

    @NotNull
    @Override
    public MessageAction editMessage(@NotNull Message message) {
        return null;
    }

    @NotNull
    @Override
    public AuditableRestAction<Void> delete() {
        return null;
    }

    @NotNull
    @Override
    public JDA getJDA() {
        return original.getJDA();
    }

    @Override
    public boolean isPinned() {
        return false;
    }

    @NotNull
    @Override
    public RestAction<Void> pin() {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> unpin() {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> addReaction(@NotNull Emote emote) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> addReaction(@NotNull String s) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactions() {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactions(@NotNull String s) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> clearReactions(@NotNull Emote emote) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull Emote emote) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull Emote emote, @NotNull User user) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull String s) {
        return null;
    }

    @NotNull
    @Override
    public RestAction<Void> removeReaction(@NotNull String s, @NotNull User user) {
        return null;
    }

    @NotNull
    @Override
    public ReactionPaginationAction retrieveReactionUsers(@NotNull Emote emote) {
        return null;
    }

    @NotNull
    @Override
    public ReactionPaginationAction retrieveReactionUsers(@NotNull String s) {
        return null;
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionByUnicode(@NotNull String s) {
        return null;
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionById(@NotNull String s) {
        return null;
    }

    @Nullable
    @Override
    public MessageReaction.ReactionEmote getReactionById(long l) {
        return null;
    }

    @NotNull
    @Override
    public AuditableRestAction<Void> suppressEmbeds(boolean b) {
        return null;
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
