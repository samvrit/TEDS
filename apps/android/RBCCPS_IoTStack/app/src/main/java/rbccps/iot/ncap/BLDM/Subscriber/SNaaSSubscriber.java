/*
 *
 * $RCSfile: BLEEventRegister.java $
 *
 * Copyright (c) 2015, RBCCPS, IISc Bangalore.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *    -	Redistributions of source code must retain the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer.
 *    -	Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer in the documentation and/or other materials provided
 *      with the distribution.
 *    -	Neither the name of RBCCPS, IISc Bangalore nor the names
 *      of its contributors may be used to endorse or promote products
 *      derived from this software without specific prior written
 *      permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package rbccps.iot.ncap.BLDM.Subscriber;

import android.util.Log;

import de.greenrobot.event.EventBus;
import rbccps.iot.ncap.BLDM.Publisher.BLDMPublisher;
import rbccps.iot.ncap.DA.Processing.BeaconNameDB;
import rbccps.iot.ncap.SNaaS.DP.EventBus.Events.SNaaSBeaconData;
import rbccps.iot.ncap.SNaaS.DP.EventBus.Events.SNaaSRSSIData;
import rbccps.iot.ncap.SNaaS.DP.EventBus.Events.SNaaSTimeStampData;

public class SNaaSSubscriber {
	
	@SuppressWarnings("deprecation")
	public void register() {
		// TODO Auto-generated method stub

		EventBus.getDefault().registerSticky(this, SNaaSBeaconData.class);
		EventBus.getDefault().registerSticky(this, SNaaSTimeStampData.class);
		EventBus.getDefault().registerSticky(this, SNaaSRSSIData.class);
		Log.i("SNaaSSubscriber", "Registered");
	}

    public void onEvent(SNaaSRSSIData SNaaSRSSIData){
        Log.e("SNaaSSubscriber","onEvent");
        BeaconNameDB.setRSSI(SNaaSRSSIData.getData());
        BeaconNameDB.settimeStamp(SNaaSTimeStampData.getData());
        BeaconNameDB.setBeaconName(SNaaSBeaconData.getData());
        BLDMPublisher.publishEvents();
       }

    public void onEvent(SNaaSTimeStampData SNaaSTimeStampData){}

    public void onEvent(SNaaSBeaconData data){}
    }
