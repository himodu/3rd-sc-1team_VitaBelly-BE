package GDSCKNU.VitaBelly.service;

import GDSCKNU.VitaBelly.model.Maid;
import GDSCKNU.VitaBelly.entity.*;
import GDSCKNU.VitaBelly.repository.maidInfoRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

//    public CompletableFuture<Void> updateMaid(String maidId, Maid maid) {
//        CompletableFuture<Void> result = new CompletableFuture<>();
//
//        databaseReference.child(maidId).setValue(maid, (databaseError, databaseReference) -> {
//            if (databaseError != null) {
//                result.completeExceptionally(databaseError.toException());
//            } else {
//                result.complete(null);
//            }
//        });
//
//        return result;
//    }
//
//    public CompletableFuture<Void> deleteMaid(String maidId) {
//        CompletableFuture<Void> result = new CompletableFuture<>();
//
//        databaseReference.child(maidId).removeValue((databaseError, databaseReference) -> {
//            if (databaseError != null) {
//                result.completeExceptionally(databaseError.toException());
//            } else {
//                result.complete(null);
//            }
//        });
//
//        return result;
//    }
}
