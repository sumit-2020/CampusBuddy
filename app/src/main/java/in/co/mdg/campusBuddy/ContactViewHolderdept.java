package in.co.mdg.campusBuddy;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by root on 13/6/15.
 */
public class ContactViewHolderdept extends RecyclerView.ViewHolder {
    public TextView nameT;
    public CardView cardview;
    public ImageButton call,email;
    public LinearLayout layout_text_call;
    public ContactViewHolderdept(View itemView)
    {
        super(itemView);
        nameT=(TextView)itemView.findViewById(R.id.name);
        call=(ImageButton)itemView.findViewById(R.id.imageButtoncall);
        email=(ImageButton)itemView.findViewById(R.id.imageButtonemail);
        cardview=(CardView)itemView.findViewById(R.id.card);
        layout_text_call=(LinearLayout)itemView.findViewById(R.id.layout_text_call);
    }
}
