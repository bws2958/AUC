/*
 * Copyright (C) 2017 Samsung Electronics Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.artik.example.hellocloud.Util;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cloud.artik.example.hellocloud.R;

public class Config {
    public static final String CLIENT_ID = "042ba26fa4e14cdea03ce466ed4197b6"; // AKA application ID
    public static final String DEVICE_ID = "f6313dd0c6ff4470a6945ad8779f82e1";


    // SharedPref
    public static final String USER_DATA = "USER_DATA";
    public static final String ID = "USER_ID";
    public static final String EMAIL = "USER_EMAIL";
    public static final String SIGNIN = "USER_SIGNIN";
    public static final String DEVICE_ID_LIST = "USER_DEVICE";
    public static final String ACCESS_TOKEN = "USER_ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "USER_REFRESH_TOKEN";
    public static final String Tab_list [] = new String[]{"블렌더", "원액기", "스마트발효기", "슬로우쿠커", "사용자소식", "이벤트"};
    public static final int AUC_Suges_img [] = new int[]{R.drawable.bean_noodle, R.drawable.solo_kimchi, R.drawable.green_juice, R.drawable.apple_jam, R.drawable.blue_blood, R.drawable.bit_lemon};
    public static final int User_Hot_img [] = new int[]{R.drawable.sul_hyun, R.drawable.ji_min, R.drawable.seul_gi, R.drawable.irene};


    // Request code
    public static final int REQ_SIGNUP = 0;
    public static final int REQ_SIGNIN = 1;

    // Response code
    public static final int RES_SUCCESS = 0;
    public static final int RES_FAIL = 1;


    // MUST be consistent with "AUTH REDIRECT URL" of your application set up at the developer.artik.cloud
    static final String REDIRECT_URI = "cloud.artik.example.hellocloud://oauth2callback";
}