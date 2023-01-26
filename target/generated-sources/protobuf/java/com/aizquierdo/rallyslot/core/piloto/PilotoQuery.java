// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PilotoGrpcService.proto

package com.aizquierdo.rallyslot.core.piloto;

/**
 * Protobuf type {@code PilotoQuery}
 */
public  final class PilotoQuery extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:PilotoQuery)
    PilotoQueryOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PilotoQuery.newBuilder() to construct.
  private PilotoQuery(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PilotoQuery() {
    pilotoId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PilotoQuery(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            pilotoId_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.aizquierdo.rallyslot.core.piloto.PilotoGrpcServiceOuterClass.internal_static_PilotoQuery_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.aizquierdo.rallyslot.core.piloto.PilotoGrpcServiceOuterClass.internal_static_PilotoQuery_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.aizquierdo.rallyslot.core.piloto.PilotoQuery.class, com.aizquierdo.rallyslot.core.piloto.PilotoQuery.Builder.class);
  }

  public static final int PILOTOID_FIELD_NUMBER = 1;
  private long pilotoId_;
  /**
   * <code>int64 pilotoId = 1;</code>
   */
  public long getPilotoId() {
    return pilotoId_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (pilotoId_ != 0L) {
      output.writeInt64(1, pilotoId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (pilotoId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, pilotoId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.aizquierdo.rallyslot.core.piloto.PilotoQuery)) {
      return super.equals(obj);
    }
    com.aizquierdo.rallyslot.core.piloto.PilotoQuery other = (com.aizquierdo.rallyslot.core.piloto.PilotoQuery) obj;

    boolean result = true;
    result = result && (getPilotoId()
        == other.getPilotoId());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PILOTOID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPilotoId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.aizquierdo.rallyslot.core.piloto.PilotoQuery prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code PilotoQuery}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:PilotoQuery)
      com.aizquierdo.rallyslot.core.piloto.PilotoQueryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.aizquierdo.rallyslot.core.piloto.PilotoGrpcServiceOuterClass.internal_static_PilotoQuery_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.aizquierdo.rallyslot.core.piloto.PilotoGrpcServiceOuterClass.internal_static_PilotoQuery_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.aizquierdo.rallyslot.core.piloto.PilotoQuery.class, com.aizquierdo.rallyslot.core.piloto.PilotoQuery.Builder.class);
    }

    // Construct using com.aizquierdo.rallyslot.core.piloto.PilotoQuery.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      pilotoId_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.aizquierdo.rallyslot.core.piloto.PilotoGrpcServiceOuterClass.internal_static_PilotoQuery_descriptor;
    }

    @java.lang.Override
    public com.aizquierdo.rallyslot.core.piloto.PilotoQuery getDefaultInstanceForType() {
      return com.aizquierdo.rallyslot.core.piloto.PilotoQuery.getDefaultInstance();
    }

    @java.lang.Override
    public com.aizquierdo.rallyslot.core.piloto.PilotoQuery build() {
      com.aizquierdo.rallyslot.core.piloto.PilotoQuery result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.aizquierdo.rallyslot.core.piloto.PilotoQuery buildPartial() {
      com.aizquierdo.rallyslot.core.piloto.PilotoQuery result = new com.aizquierdo.rallyslot.core.piloto.PilotoQuery(this);
      result.pilotoId_ = pilotoId_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.aizquierdo.rallyslot.core.piloto.PilotoQuery) {
        return mergeFrom((com.aizquierdo.rallyslot.core.piloto.PilotoQuery)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.aizquierdo.rallyslot.core.piloto.PilotoQuery other) {
      if (other == com.aizquierdo.rallyslot.core.piloto.PilotoQuery.getDefaultInstance()) return this;
      if (other.getPilotoId() != 0L) {
        setPilotoId(other.getPilotoId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.aizquierdo.rallyslot.core.piloto.PilotoQuery parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.aizquierdo.rallyslot.core.piloto.PilotoQuery) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long pilotoId_ ;
    /**
     * <code>int64 pilotoId = 1;</code>
     */
    public long getPilotoId() {
      return pilotoId_;
    }
    /**
     * <code>int64 pilotoId = 1;</code>
     */
    public Builder setPilotoId(long value) {
      
      pilotoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 pilotoId = 1;</code>
     */
    public Builder clearPilotoId() {
      
      pilotoId_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:PilotoQuery)
  }

  // @@protoc_insertion_point(class_scope:PilotoQuery)
  private static final com.aizquierdo.rallyslot.core.piloto.PilotoQuery DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.aizquierdo.rallyslot.core.piloto.PilotoQuery();
  }

  public static com.aizquierdo.rallyslot.core.piloto.PilotoQuery getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PilotoQuery>
      PARSER = new com.google.protobuf.AbstractParser<PilotoQuery>() {
    @java.lang.Override
    public PilotoQuery parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new PilotoQuery(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PilotoQuery> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PilotoQuery> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.aizquierdo.rallyslot.core.piloto.PilotoQuery getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

