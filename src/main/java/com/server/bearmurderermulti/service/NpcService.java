package com.server.bearmurderermulti.service;

import com.server.bearmurderermulti.domain.dto.npc.*;
import com.server.bearmurderermulti.domain.entity.Npc;
import com.server.bearmurderermulti.exception.AppException;
import com.server.bearmurderermulti.exception.ErrorCode;
import com.server.bearmurderermulti.repository.NpcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NpcService {

    private final NpcRepository npcRepository;

    @Transactional
    public EnrollNpcResponse enroll(EnrollNpcRequest request) {

        Npc savedNpc = npcRepository.save(Npc.builder()
                .npcName(request.getNpcName())
                .npcPersonality(request.getNpcPersonality())
                .npcFeature(request.getNpcFeature())
                .build());

        return new EnrollNpcResponse(savedNpc);
    }

    @Transactional
    public UpdateNpcResponse update(UpdateNpcRequest request, long npcNo) {

        Npc existNpc = validateNpcByNo(npcNo);

        existNpc.updateNpc(request);

        return new UpdateNpcResponse(existNpc);
    }

    public ReadNpcResponse readByNo(long npcNo) {

        Npc existNpc = validateNpcByNo(npcNo);

        return new ReadNpcResponse(existNpc);
    }

    public ReadNpcResponse readByName(String npcName) {

        Npc existNpc = validateNpcByName(npcName);

        return new ReadNpcResponse(existNpc);
    }

    public Page<ReadAllNpcResponse> readAll(PageRequest pageable) {

        return npcRepository.findAll(pageable).map(ReadAllNpcResponse::of);
    }

    private Npc validateNpcByName(String npcName) {
        return npcRepository.findByNpcName(npcName)
                .orElseThrow(() -> new AppException(ErrorCode.NPC_NOT_FOUND));
    }

    private Npc validateNpcByNo(long npcNo) {
        return npcRepository.findByNpcNo(npcNo)
                .orElseThrow(() -> new AppException(ErrorCode.NPC_NOT_FOUND));
    }
}
