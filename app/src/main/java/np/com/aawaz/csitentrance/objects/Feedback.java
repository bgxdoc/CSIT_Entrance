package np.com.aawaz.csitentrance.objects;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.iid.FirebaseInstanceId;

@IgnoreExtraProperties
public class Feedback {

    public String name;
    public String email;
    public String message;
    public String instance_id;

    public Feedback() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Feedback(String message) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        this.name = user.getDisplayName();
        this.email = user.getEmail();
        this.message = message;
        this.instance_id = FirebaseInstanceId.getInstance().getToken();
    }

}