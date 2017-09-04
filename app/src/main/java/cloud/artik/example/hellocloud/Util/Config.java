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

public class Config {
    public static final String CLIENT_ID = "042ba26fa4e14cdea03ce466ed4197b6"; // AKA application ID
    public static final String DEVICE_ID = "f6313dd0c6ff4470a6945ad8779f82e1";

    // MUST be consistent with "AUTH REDIRECT URL" of your application set up at the developer.artik.cloud
    static final String REDIRECT_URI = "cloud.artik.example.hellocloud://oauth2callback";
}