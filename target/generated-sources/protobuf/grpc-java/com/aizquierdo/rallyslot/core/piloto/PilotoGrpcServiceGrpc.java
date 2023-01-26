package com.aizquierdo.rallyslot.core.piloto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.22.1)",
    comments = "Source: PilotoGrpcService.proto")
public final class PilotoGrpcServiceGrpc {

  private PilotoGrpcServiceGrpc() {}

  public static final String SERVICE_NAME = "PilotoGrpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoQuery,
      com.aizquierdo.rallyslot.core.piloto.PilotoData> getGetPilotoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPiloto",
      requestType = com.aizquierdo.rallyslot.core.piloto.PilotoQuery.class,
      responseType = com.aizquierdo.rallyslot.core.piloto.PilotoData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoQuery,
      com.aizquierdo.rallyslot.core.piloto.PilotoData> getGetPilotoMethod() {
    io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoQuery, com.aizquierdo.rallyslot.core.piloto.PilotoData> getGetPilotoMethod;
    if ((getGetPilotoMethod = PilotoGrpcServiceGrpc.getGetPilotoMethod) == null) {
      synchronized (PilotoGrpcServiceGrpc.class) {
        if ((getGetPilotoMethod = PilotoGrpcServiceGrpc.getGetPilotoMethod) == null) {
          PilotoGrpcServiceGrpc.getGetPilotoMethod = getGetPilotoMethod = 
              io.grpc.MethodDescriptor.<com.aizquierdo.rallyslot.core.piloto.PilotoQuery, com.aizquierdo.rallyslot.core.piloto.PilotoData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "PilotoGrpcService", "getPiloto"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aizquierdo.rallyslot.core.piloto.PilotoQuery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aizquierdo.rallyslot.core.piloto.PilotoData.getDefaultInstance()))
                  .setSchemaDescriptor(new PilotoGrpcServiceMethodDescriptorSupplier("getPiloto"))
                  .build();
          }
        }
     }
     return getGetPilotoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.aizquierdo.rallyslot.core.piloto.PilotoData> getGetPilotosMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPilotos",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.aizquierdo.rallyslot.core.piloto.PilotoData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.aizquierdo.rallyslot.core.piloto.PilotoData> getGetPilotosMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.aizquierdo.rallyslot.core.piloto.PilotoData> getGetPilotosMethod;
    if ((getGetPilotosMethod = PilotoGrpcServiceGrpc.getGetPilotosMethod) == null) {
      synchronized (PilotoGrpcServiceGrpc.class) {
        if ((getGetPilotosMethod = PilotoGrpcServiceGrpc.getGetPilotosMethod) == null) {
          PilotoGrpcServiceGrpc.getGetPilotosMethod = getGetPilotosMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.aizquierdo.rallyslot.core.piloto.PilotoData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "PilotoGrpcService", "getPilotos"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aizquierdo.rallyslot.core.piloto.PilotoData.getDefaultInstance()))
                  .setSchemaDescriptor(new PilotoGrpcServiceMethodDescriptorSupplier("getPilotos"))
                  .build();
          }
        }
     }
     return getGetPilotosMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoData,
      com.aizquierdo.rallyslot.core.piloto.PilotoData> getCreatePilotoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createPiloto",
      requestType = com.aizquierdo.rallyslot.core.piloto.PilotoData.class,
      responseType = com.aizquierdo.rallyslot.core.piloto.PilotoData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoData,
      com.aizquierdo.rallyslot.core.piloto.PilotoData> getCreatePilotoMethod() {
    io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoData, com.aizquierdo.rallyslot.core.piloto.PilotoData> getCreatePilotoMethod;
    if ((getCreatePilotoMethod = PilotoGrpcServiceGrpc.getCreatePilotoMethod) == null) {
      synchronized (PilotoGrpcServiceGrpc.class) {
        if ((getCreatePilotoMethod = PilotoGrpcServiceGrpc.getCreatePilotoMethod) == null) {
          PilotoGrpcServiceGrpc.getCreatePilotoMethod = getCreatePilotoMethod = 
              io.grpc.MethodDescriptor.<com.aizquierdo.rallyslot.core.piloto.PilotoData, com.aizquierdo.rallyslot.core.piloto.PilotoData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "PilotoGrpcService", "createPiloto"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aizquierdo.rallyslot.core.piloto.PilotoData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aizquierdo.rallyslot.core.piloto.PilotoData.getDefaultInstance()))
                  .setSchemaDescriptor(new PilotoGrpcServiceMethodDescriptorSupplier("createPiloto"))
                  .build();
          }
        }
     }
     return getCreatePilotoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoData,
      com.aizquierdo.rallyslot.core.piloto.PilotoData> getUpdatePilotoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updatePiloto",
      requestType = com.aizquierdo.rallyslot.core.piloto.PilotoData.class,
      responseType = com.aizquierdo.rallyslot.core.piloto.PilotoData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoData,
      com.aizquierdo.rallyslot.core.piloto.PilotoData> getUpdatePilotoMethod() {
    io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoData, com.aizquierdo.rallyslot.core.piloto.PilotoData> getUpdatePilotoMethod;
    if ((getUpdatePilotoMethod = PilotoGrpcServiceGrpc.getUpdatePilotoMethod) == null) {
      synchronized (PilotoGrpcServiceGrpc.class) {
        if ((getUpdatePilotoMethod = PilotoGrpcServiceGrpc.getUpdatePilotoMethod) == null) {
          PilotoGrpcServiceGrpc.getUpdatePilotoMethod = getUpdatePilotoMethod = 
              io.grpc.MethodDescriptor.<com.aizquierdo.rallyslot.core.piloto.PilotoData, com.aizquierdo.rallyslot.core.piloto.PilotoData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "PilotoGrpcService", "updatePiloto"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aizquierdo.rallyslot.core.piloto.PilotoData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aizquierdo.rallyslot.core.piloto.PilotoData.getDefaultInstance()))
                  .setSchemaDescriptor(new PilotoGrpcServiceMethodDescriptorSupplier("updatePiloto"))
                  .build();
          }
        }
     }
     return getUpdatePilotoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoQuery,
      com.google.protobuf.Empty> getDeletePilotoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deletePiloto",
      requestType = com.aizquierdo.rallyslot.core.piloto.PilotoQuery.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoQuery,
      com.google.protobuf.Empty> getDeletePilotoMethod() {
    io.grpc.MethodDescriptor<com.aizquierdo.rallyslot.core.piloto.PilotoQuery, com.google.protobuf.Empty> getDeletePilotoMethod;
    if ((getDeletePilotoMethod = PilotoGrpcServiceGrpc.getDeletePilotoMethod) == null) {
      synchronized (PilotoGrpcServiceGrpc.class) {
        if ((getDeletePilotoMethod = PilotoGrpcServiceGrpc.getDeletePilotoMethod) == null) {
          PilotoGrpcServiceGrpc.getDeletePilotoMethod = getDeletePilotoMethod = 
              io.grpc.MethodDescriptor.<com.aizquierdo.rallyslot.core.piloto.PilotoQuery, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "PilotoGrpcService", "deletePiloto"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.aizquierdo.rallyslot.core.piloto.PilotoQuery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new PilotoGrpcServiceMethodDescriptorSupplier("deletePiloto"))
                  .build();
          }
        }
     }
     return getDeletePilotoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PilotoGrpcServiceStub newStub(io.grpc.Channel channel) {
    return new PilotoGrpcServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PilotoGrpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PilotoGrpcServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PilotoGrpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PilotoGrpcServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PilotoGrpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getPiloto(com.aizquierdo.rallyslot.core.piloto.PilotoQuery request,
        io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPilotoMethod(), responseObserver);
    }

    /**
     */
    public void getPilotos(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPilotosMethod(), responseObserver);
    }

    /**
     */
    public void createPiloto(com.aizquierdo.rallyslot.core.piloto.PilotoData request,
        io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData> responseObserver) {
      asyncUnimplementedUnaryCall(getCreatePilotoMethod(), responseObserver);
    }

    /**
     */
    public void updatePiloto(com.aizquierdo.rallyslot.core.piloto.PilotoData request,
        io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdatePilotoMethod(), responseObserver);
    }

    /**
     */
    public void deletePiloto(com.aizquierdo.rallyslot.core.piloto.PilotoQuery request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getDeletePilotoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetPilotoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aizquierdo.rallyslot.core.piloto.PilotoQuery,
                com.aizquierdo.rallyslot.core.piloto.PilotoData>(
                  this, METHODID_GET_PILOTO)))
          .addMethod(
            getGetPilotosMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                com.aizquierdo.rallyslot.core.piloto.PilotoData>(
                  this, METHODID_GET_PILOTOS)))
          .addMethod(
            getCreatePilotoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aizquierdo.rallyslot.core.piloto.PilotoData,
                com.aizquierdo.rallyslot.core.piloto.PilotoData>(
                  this, METHODID_CREATE_PILOTO)))
          .addMethod(
            getUpdatePilotoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aizquierdo.rallyslot.core.piloto.PilotoData,
                com.aizquierdo.rallyslot.core.piloto.PilotoData>(
                  this, METHODID_UPDATE_PILOTO)))
          .addMethod(
            getDeletePilotoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.aizquierdo.rallyslot.core.piloto.PilotoQuery,
                com.google.protobuf.Empty>(
                  this, METHODID_DELETE_PILOTO)))
          .build();
    }
  }

  /**
   */
  public static final class PilotoGrpcServiceStub extends io.grpc.stub.AbstractStub<PilotoGrpcServiceStub> {
    private PilotoGrpcServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PilotoGrpcServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PilotoGrpcServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PilotoGrpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void getPiloto(com.aizquierdo.rallyslot.core.piloto.PilotoQuery request,
        io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPilotoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPilotos(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetPilotosMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createPiloto(com.aizquierdo.rallyslot.core.piloto.PilotoData request,
        io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreatePilotoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePiloto(com.aizquierdo.rallyslot.core.piloto.PilotoData request,
        io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdatePilotoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deletePiloto(com.aizquierdo.rallyslot.core.piloto.PilotoQuery request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeletePilotoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PilotoGrpcServiceBlockingStub extends io.grpc.stub.AbstractStub<PilotoGrpcServiceBlockingStub> {
    private PilotoGrpcServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PilotoGrpcServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PilotoGrpcServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PilotoGrpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.aizquierdo.rallyslot.core.piloto.PilotoData getPiloto(com.aizquierdo.rallyslot.core.piloto.PilotoQuery request) {
      return blockingUnaryCall(
          getChannel(), getGetPilotoMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.aizquierdo.rallyslot.core.piloto.PilotoData> getPilotos(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getGetPilotosMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aizquierdo.rallyslot.core.piloto.PilotoData createPiloto(com.aizquierdo.rallyslot.core.piloto.PilotoData request) {
      return blockingUnaryCall(
          getChannel(), getCreatePilotoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.aizquierdo.rallyslot.core.piloto.PilotoData updatePiloto(com.aizquierdo.rallyslot.core.piloto.PilotoData request) {
      return blockingUnaryCall(
          getChannel(), getUpdatePilotoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deletePiloto(com.aizquierdo.rallyslot.core.piloto.PilotoQuery request) {
      return blockingUnaryCall(
          getChannel(), getDeletePilotoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PilotoGrpcServiceFutureStub extends io.grpc.stub.AbstractStub<PilotoGrpcServiceFutureStub> {
    private PilotoGrpcServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PilotoGrpcServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PilotoGrpcServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PilotoGrpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aizquierdo.rallyslot.core.piloto.PilotoData> getPiloto(
        com.aizquierdo.rallyslot.core.piloto.PilotoQuery request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPilotoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aizquierdo.rallyslot.core.piloto.PilotoData> createPiloto(
        com.aizquierdo.rallyslot.core.piloto.PilotoData request) {
      return futureUnaryCall(
          getChannel().newCall(getCreatePilotoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.aizquierdo.rallyslot.core.piloto.PilotoData> updatePiloto(
        com.aizquierdo.rallyslot.core.piloto.PilotoData request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdatePilotoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deletePiloto(
        com.aizquierdo.rallyslot.core.piloto.PilotoQuery request) {
      return futureUnaryCall(
          getChannel().newCall(getDeletePilotoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PILOTO = 0;
  private static final int METHODID_GET_PILOTOS = 1;
  private static final int METHODID_CREATE_PILOTO = 2;
  private static final int METHODID_UPDATE_PILOTO = 3;
  private static final int METHODID_DELETE_PILOTO = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PilotoGrpcServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PilotoGrpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PILOTO:
          serviceImpl.getPiloto((com.aizquierdo.rallyslot.core.piloto.PilotoQuery) request,
              (io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData>) responseObserver);
          break;
        case METHODID_GET_PILOTOS:
          serviceImpl.getPilotos((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData>) responseObserver);
          break;
        case METHODID_CREATE_PILOTO:
          serviceImpl.createPiloto((com.aizquierdo.rallyslot.core.piloto.PilotoData) request,
              (io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData>) responseObserver);
          break;
        case METHODID_UPDATE_PILOTO:
          serviceImpl.updatePiloto((com.aizquierdo.rallyslot.core.piloto.PilotoData) request,
              (io.grpc.stub.StreamObserver<com.aizquierdo.rallyslot.core.piloto.PilotoData>) responseObserver);
          break;
        case METHODID_DELETE_PILOTO:
          serviceImpl.deletePiloto((com.aizquierdo.rallyslot.core.piloto.PilotoQuery) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PilotoGrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PilotoGrpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.aizquierdo.rallyslot.core.piloto.PilotoGrpcServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PilotoGrpcService");
    }
  }

  private static final class PilotoGrpcServiceFileDescriptorSupplier
      extends PilotoGrpcServiceBaseDescriptorSupplier {
    PilotoGrpcServiceFileDescriptorSupplier() {}
  }

  private static final class PilotoGrpcServiceMethodDescriptorSupplier
      extends PilotoGrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PilotoGrpcServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PilotoGrpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PilotoGrpcServiceFileDescriptorSupplier())
              .addMethod(getGetPilotoMethod())
              .addMethod(getGetPilotosMethod())
              .addMethod(getCreatePilotoMethod())
              .addMethod(getUpdatePilotoMethod())
              .addMethod(getDeletePilotoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
