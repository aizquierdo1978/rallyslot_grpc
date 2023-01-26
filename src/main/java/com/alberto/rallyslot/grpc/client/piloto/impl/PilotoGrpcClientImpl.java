package com.alberto.rallyslot.grpc.client.piloto.impl;

import com.aizquierdo.rallyslot.core.piloto.PilotoData;
import com.aizquierdo.rallyslot.core.piloto.PilotoGrpcServiceGrpc;
import com.aizquierdo.rallyslot.core.piloto.PilotoQuery;
import com.alberto.rallyslot.entity.piloto.PilotoEntity;
import com.alberto.rallyslot.exception.EntityNotFoundException;
import com.alberto.rallyslot.grpc.client.piloto.PilotoGrpcClient;
import com.alberto.rallyslot.util.RallyslotConstants;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PilotoGrpcClientImpl implements PilotoGrpcClient {

    @Autowired
    private ManagedChannel channel;

    private PilotoGrpcClientImpl() {
    }

    @Override
    public PilotoEntity findById(Long pilotoId) {
        PilotoData pilotoData = null;
        try {
            PilotoQuery pilotoquery = PilotoQuery.newBuilder()
                .setPilotoId(pilotoId)
                .build();
            pilotoData = getBlockingStub().getPiloto(pilotoquery);
        } catch (StatusRuntimeException e) {
            if(e.getStatus().equals(Status.NOT_FOUND)) {
                throw new EntityNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return parsePilotoEntity(pilotoData);
    }

    @Override
    public PilotoEntity savePiloto(PilotoEntity piloto) {
        PilotoData pilotoData = parsePilotoData(piloto);
        PilotoData pilotoCreated = getBlockingStub().createPiloto(pilotoData);
        return parsePilotoEntity(pilotoCreated);
    }

    @Override
    public List<PilotoEntity> fetchPilotoList() {
        List<PilotoEntity> listaPilotos = new ArrayList<>();
        getBlockingStub().getPilotos(Empty.newBuilder().build()).forEachRemaining(pilotoData -> listaPilotos.add(parsePilotoEntity(pilotoData)));
        return listaPilotos;
    }

    @Override
    public PilotoEntity updatePiloto(PilotoEntity piloto, Long pilotoId) {
        PilotoData pilotoData = null;
        try{
            pilotoData = getBlockingStub().updatePiloto(parsePilotoData(piloto));
        } catch (StatusRuntimeException e) {
            if(e.getStatus().equals(Status.NOT_FOUND)) {
                throw new EntityNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return parsePilotoEntity(pilotoData);
    }

    @Override
    public void deletePilotoById(PilotoEntity piloto) {
        PilotoQuery pilotoquery = PilotoQuery.newBuilder()
                .setPilotoId(piloto.getPilotoId())
                .build();
        try {
            getBlockingStub().deletePiloto(pilotoquery);
        } catch (StatusRuntimeException e) {
            if(e.getStatus().equals(Status.NOT_FOUND)) {
                throw new EntityNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private PilotoData parsePilotoData(PilotoEntity piloto) {
        if(piloto == null) {
            return null;
        }
        PilotoData.Builder pilotoDataBuilder = PilotoData.newBuilder();

        if(piloto.getPilotoId()!=null) {
            pilotoDataBuilder.setPilotoId(piloto.getPilotoId());
        }

        if(piloto.getPilotoName()!=null) {
            pilotoDataBuilder.setPilotoName(piloto.getPilotoName());
        }

        if(piloto.getCreationUser()!=null) {
            pilotoDataBuilder.setCreationUser(piloto.getCreationUser());
        }

        if(piloto.getCreationDate()!=null) {
            pilotoDataBuilder.setCreationDate(piloto.getCreationDate().getTime());
        }

        if(piloto.getModificationUser()!=null) {
            pilotoDataBuilder.setModificationUser(piloto.getModificationUser());
        }

        if(piloto.getModificationDate()!=null) {
            pilotoDataBuilder.setModificationDate(piloto.getModificationDate().getTime());
        }

        if(piloto.getDeleteUser()!=null) {
            pilotoDataBuilder.setDeleteUser(piloto.getDeleteUser());
        }

        if(piloto.getDeleteDate()!=null) {
            pilotoDataBuilder.setDeleteDate(piloto.getDeleteDate().getTime());
        }

        return pilotoDataBuilder.build();
    }

    private PilotoEntity parsePilotoEntity(PilotoData pilotoData) {
        if(pilotoData == null) {
            return null;
        }

        PilotoEntity piloto = new PilotoEntity();
        piloto.setPilotoId(pilotoData.getPilotoId());
        piloto.setPilotoName(pilotoData.getPilotoName());
        piloto.setCreationUser(pilotoData.getCreationUser());
        if(pilotoData.getCreationDate()> RallyslotConstants.ZERO) {
            piloto.setCreationDate(new Date(pilotoData.getCreationDate()));
        }
        piloto.setModificationUser(pilotoData.getModificationUser());
        if(pilotoData.getModificationDate()> RallyslotConstants.ZERO) {
            piloto.setModificationDate(new Date(pilotoData.getModificationDate()));
        }
        piloto.setDeleteUser(pilotoData.getDeleteUser());
        if(pilotoData.getDeleteDate()> RallyslotConstants.ZERO) {
            piloto.setDeleteDate(new Date(pilotoData.getDeleteDate()));
        }
        return piloto;
    }

    private PilotoGrpcServiceGrpc.PilotoGrpcServiceBlockingStub getBlockingStub() {
        return PilotoGrpcServiceGrpc.newBlockingStub(channel);
    }

}
