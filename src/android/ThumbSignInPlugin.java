/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/
package com.pramati.cordova.plugin;

import android.util.Log;

import com.pramati.thumbsignin.ThumbSignIn;
import com.pramati.thumbsignin.simplified.TSErrorCode;
import com.pramati.thumbsignin.simplified.TSResponse;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThumbSignInPlugin extends CordovaPlugin {
    private static final String TAG = "ThumbSignIn";
    
    private static ThumbSignIn.Callback getCallback(CallbackContext callbackContext) {
        return new ThumbSignIn.Callback() {
            @Override
            public void onSuccess(TSResponse tsResponse) {
                callbackContext.success(tsResponse == null ? "" : tsResponse.toJson());
            }

            @Override
            public void onFailure(TSErrorCode tsErrorCode) {
                callbackContext.error(tsErrorCode.code);
            }
        };
    }

    private static boolean THUMB_SIGN_IN_INITIALIZED = false;


    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (!THUMB_SIGN_IN_INITIALIZED) {
            ThumbSignIn.init(this.cordova.getContext());
            THUMB_SIGN_IN_INITIALIZED = true;
        }
        if ("register".equals(action)) {
            ThumbSignIn.register(args.getString(0), getCallback(callbackContext));
        } else if ("login".equals(action)) {
            ThumbSignIn.authenticate(getCallback(callbackContext));
        } else if ("deregister".equals(action)) {
            ThumbSignIn.dereg(getCallback(callbackContext));
        } else {
            return false;
        }
        return true;
    }
}





