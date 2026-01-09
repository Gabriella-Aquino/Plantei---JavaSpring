package br.com.gabriella.plantei.service;

import br.com.gabriella.plantei.dtos.GardenMember.GardenMemberCreateDTO;
import br.com.gabriella.plantei.dtos.GardenMember.GardenMemberReadDTO;
import br.com.gabriella.plantei.exception.ResourceNotFoundException;
import br.com.gabriella.plantei.mapper.GardenMemberMapper;
import br.com.gabriella.plantei.model.Garden;
import br.com.gabriella.plantei.model.GardenMember;
import br.com.gabriella.plantei.model.User;
import br.com.gabriella.plantei.repository.GardenMemberRepository;
import br.com.gabriella.plantei.repository.GardenRepository;
import br.com.gabriella.plantei.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GardenMemberService {
    @Autowired
    private GardenMemberRepository gardenMemberRepository;

    @Autowired
    private GardenMemberMapper gardenMemberMapper;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private UserRepository userRepository;


    public GardenMemberReadDTO createGardenMember(
            Long gardenId,
            GardenMemberCreateDTO dto
    ) {
        Garden garden = gardenRepository.findById(gardenId)
                .orElseThrow(() -> new ResourceNotFoundException("Jardim não encontrado"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        GardenMember member = new GardenMember();
        member.setGarden(garden);
        member.setUser(user);

        gardenMemberRepository.save(member);

        return gardenMemberMapper.toReadDTO(member);
    }


    public List<GardenMemberReadDTO> getMembersByGarden(Long gardenId) {
        return gardenMemberRepository.findByGardenId(gardenId)
                .stream()
                .map(gardenMemberMapper::toReadDTO)
                .toList();
    }


    public GardenMemberReadDTO getGardenMemberById(long id){
        GardenMember gardenMember = gardenMemberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Membro não encontrado"));
        return gardenMemberMapper.toReadDTO(gardenMember);
    }

    public void removeMember(Long gardenId, Long userId) {
        GardenMember member = gardenMemberRepository
                .findByGardenIdAndUserId(gardenId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Membro não encontrado"));

        gardenMemberRepository.delete(member);
    }

    public void deleteGardenMember(long id){
        GardenMember gardenMember = gardenMemberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Membro não encontrado"));
        gardenMemberRepository.delete(gardenMember);
    }
}
