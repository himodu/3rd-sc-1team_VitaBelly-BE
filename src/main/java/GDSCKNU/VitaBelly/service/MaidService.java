package GDSCKNU.VitaBelly.service;

import GDSCKNU.VitaBelly.model.Maid;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

@Service
public class MaidService {

    private final DatabaseReference databaseReference;

    public MaidService() {
        databaseReference = FirebaseDatabase.getInstance().getReference("maids");
    }

    public CompletableFuture<String> addMaid(Maid maid) {
        CompletableFuture<String> result = new CompletableFuture<>();
        String key = databaseReference.push().getKey();

        maid.setId(key);

        databaseReference.child(key).setValue(maid, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                result.completeExceptionally(databaseError.toException());
            } else {
                result.complete(key);
            }
        });
        return result;
    }

    public CompletableFuture<List<Maid>> getAllMaids() {
        CompletableFuture<List<Maid>> futureMaids = new CompletableFuture<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Maid> maids = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Maid maid = snapshot.getValue(Maid.class);
                    maids.add(maid);
                }
                futureMaids.complete(maids);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                futureMaids.completeExceptionally(databaseError.toException());
            }
        });

        return futureMaids;
    }

    public CompletableFuture<Void> updateMaid(String maidId, Maid maid) {
        CompletableFuture<Void> result = new CompletableFuture<>();

        databaseReference.child(maidId).setValue(maid, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                result.completeExceptionally(databaseError.toException());
            } else {
                result.complete(null);
            }
        });

        return result;
    }

    public CompletableFuture<Void> deleteMaid(String maidId) {
        CompletableFuture<Void> result = new CompletableFuture<>();

        databaseReference.child(maidId).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                result.completeExceptionally(databaseError.toException());
            } else {
                result.complete(null);
            }
        });

        return result;
    }
}
