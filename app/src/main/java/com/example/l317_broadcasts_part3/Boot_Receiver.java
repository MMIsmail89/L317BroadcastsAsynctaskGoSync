package com.example.l317_broadcasts_part3;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Toast;

public class Boot_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        //>> Note, the (Android OS) deals with the solution of this (Receiver Broad task)
        // Besides with (goAsync) and (Async Task || Thread) as a Background service.
        //        Meanwhile, the Background service can not run the (Toast),
        //                For this reason, we you (Handler.post )

        final PendingResult result_GoAsy =  goAsync(); // calling goAsync Method

//          new my_task(result_GoAsy).execute();
          new my_task(result_GoAsy, context, intent).execute();

    } // onReceive Method


    class my_task extends AsyncTask<String, Void, Void>
    {
        //
        public PendingResult result;
        public Context context;
        public Intent intent;
        //
        final Handler handler = new Handler();

        my_task(PendingResult result) {
            this.result = result;
        }


        my_task(PendingResult result, Context context, Intent intent) {
            this.result = result;
            this.context = context;
            this.intent = intent;
        }


        @Override
        protected Void doInBackground(String... strings) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Boot_Receiver.my_procedure(context,intent);
                } // run handler
            }); // handler .post runnable
            return null;
        } // doInBackground

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            result.finish(); // to stop the goAsync();

        } // onPostExecute
    } // my_task AsyncTask

    //    -0-0-0-   -9-9-9-   -0-0-0-   -9-9-9-
    static Void my_procedure(Context context, Intent intent)
    {
        //                      Thread.sleep(2000); // long consumed time code
        SystemClock.sleep(2000); // heavy code consumed time

        if (intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
            int state = intent.getIntExtra("state", -1);
            switch (state) {
                case 0:
                    Toast.makeText(context, "Headphone Unplugged, (No)\n(Powered by AsyncTask, goAsync)", Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(context, "Headphone Plugged, (Yes)\n(Powered by AsyncTask, goAsync)", Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(context, "State of the Headphone is Undefined\n(Powered by AsyncTask, goAsync)", Toast.LENGTH_LONG).show();
                    break;
            } // switch
        } // if
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Toast.makeText(context, "Cell Phone has been restarted\n(Powered by AsyncTask, goAsync)", Toast.LENGTH_LONG).show();
        } //  else if 1
        return null;
    } // my_procedure Method
} // Boot_Receiver class