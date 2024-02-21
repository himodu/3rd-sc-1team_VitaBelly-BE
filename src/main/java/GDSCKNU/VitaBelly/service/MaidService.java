package GDSCKNU.VitaBelly.service;

import GDSCKNU.VitaBelly.model.Maid;

import GDSCKNU.VitaBelly.repository.*;
import GDSCKNU.VitaBelly.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MaidService {

    private final maidInfoRepository maidInfoRepository;

    public MaidService(GDSCKNU.VitaBelly.repository.maidInfoRepository maidInfoRepository) {
        this.maidInfoRepository = maidInfoRepository;
    }

    public void addMaid(Maid maid) {

        maidInfo maidinfo = maidInfo.builder()
                .profileImageUrl(maid.getProfileImageUrl())
                .name(maid.getName())
                .introduction(maid.getIntroduction())
                .city(maid.getLocation().getCity())
                .district(maid.getLocation().getDistrict())
                .detail(maid.getLocation().getDetail())
                .ratings((int)maid.getRatings())
                .services(maid.getServices())
                .build();

        maidInfoRepository.save(maidinfo);
    }

    public List<Maid> getAllMaids() {
        List<maidInfo> maidInfos =  maidInfoRepository.findAll();
        List<Maid> maids = new ArrayList<>();

        for(maidInfo e : maidInfos){
            Maid maid = Maid.builder()
                    .id(e.getId().intValue())
                    .profileImageUrl(e.getProfileImageUrl())
                    .name(e.getName())
                    .introduction(e.getIntroduction())
                    .location(new Maid.Location(e.getCity(),e.getDistrict(),e.getDetail()))
                    .ratings(e.getRatings())
                    .services(e.getServices())
                    .build();

            maids.add(maid);
        }
        return maids;
    }

    public Maid getMaid(int id){
        Optional<maidInfo> maidinfo = maidInfoRepository.findById(Long.valueOf(id));
        if(maidinfo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        maidInfo e = maidinfo.get();

        Maid maid = Maid.builder()
                .id(e.getId().intValue())
                .profileImageUrl(e.getProfileImageUrl())
                .name(e.getName())
                .introduction(e.getIntroduction())
                .location(new Maid.Location(e.getCity(),e.getDistrict(),e.getDetail()))
                .ratings(e.getRatings())
                .services(e.getServices())
                .build();

        return maid;
    }
}
