package com.example.sylergy.presenter;

import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;

public class ActivityDispatcher {
    private static ActivityDispatcher instance;

    public static ActivityDispatcher getInstance(){
        if(instance==null)
            instance=new ActivityDispatcher();
        return instance;
    }

    public void dispatchActivity(Context context){

        try {
            // Jiali: why use switch ?
            switch(context.getEvent()) {
                case Events.SEARCH_PRODUCT_OK :
                case Events.SEARCH_PRODUCT_NAME_OK :
                case Events.CREATE_PRODUCT_OK:
                    context.getActivity().updateWithCommandResult(context);
                    break;
                case Events.SEARCH_PRODUCT_ERROR :
                case Events.SEARCH_PRODUCT_NAME_ERROR :
                case Events.CREATE_PRODUCT_ERROR:
                    context.getActivity().updateWithCommandResult(context);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
