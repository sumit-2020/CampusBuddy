package in.co.mdg.campusbuddy;

import android.app.Activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;


/**
 * Created by rc on 18/5/15.
 */
public class delete_edit_choose extends DialogFragment  {

    AlertPositiveListener alertPositiveListener;
    int position;

    private AlertPositiveListener mAlertPositiveListener;
    public interface AlertPositiveListener {
        public void onPositiveClick(int position);
    }

    // Use this instance of the interface to deliver action events


    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            alertPositiveListener = (AlertPositiveListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement ChooseDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        //getting arguments from the parent activity
        try {
            Bundle b = getArguments();
            position = b.getInt("position");
        }
        catch (Exception e)
        {
//            Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_LONG).show();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       // LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle("Choose what you want to do?");
        //builder.setView(inflater.inflate(R.layout.activity_deleteand_edit_events2, null));

        builder.setSingleChoiceItems(RadioButtons.dae, 0, null);

        builder.setPositiveButton("OK", positiveListener);
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();

        return dialog;

    }


    DialogInterface.OnClickListener positiveListener=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            AlertDialog dialog1=(AlertDialog)dialog;
            int position=dialog1.getListView().getCheckedItemPosition();
            alertPositiveListener.onPositiveClick(position);
        }
    };


}
