/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.vertcoin.params;

import com.google.vertcoin.core.NetworkParameters;
import com.google.vertcoin.core.Sha256Hash;
import com.google.vertcoin.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);
        dumpedPrivateKeyHeader = 199; // This is always addressHeader + 128
        addressHeader = 71;     // Vertcoin Starts with a V (src/base58.h -> PUBKEY_ADDRESS)
        //p2shHeader = 5; // I don't think we have this
        acceptableAddressCodes = new int[] { addressHeader };
        port = 5888;
        packetMagic = 0xfabfb5daL; // Found in src/main.cpp -> unsigned char pchMessageStart[4]
        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1389311371L);
        genesisBlock.setNonce(5749262L);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 840000;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        // Correct Hash is: 4d96a915f49d40b1e5c2844d1ee2dccb90013a990ccea12c492d22110489f0c4
        // Current hash is: 7de5776b6485ab9d8ad80a00d5f18cb586abf38282bef66d936160ae4a286169
        System.out.println("YOLO " + genesisBlock.toString());
        checkState(genesisHash.equals("7de5776b6485ab9d8ad80a00d5f18cb586abf38282bef66d936160ae4a286169"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        // TODO Get Vertcoin checkpoints
/*        checkpoints.put(91722, new Sha256Hash("00000000000271a2dc26e7667f8419f2e15416dc6955e5a6c6cdf3f2574dd08e"));
        checkpoints.put(91812, new Sha256Hash("00000000000af0aed4792b1acee3d966af36cf5def14935db8de83d6f9306f2f"));
        checkpoints.put(91842, new Sha256Hash("00000000000a4d0a398161ffc163c503763b1f4360639393e0e4c8e300e0caec"));
        checkpoints.put(91880, new Sha256Hash("00000000000743f190a18c5577a3c2d2a1f610ae9601ac046a38084ccb7cd721"));
        checkpoints.put(200000, new Sha256Hash("000000000000034a7dedef4a161fa058a2d67a173a90155f3a2fe6fc132e0ebf"));*/

        dnsSeeds = new String[] {
            "ny.vertcoin.org",
            "la.vertcoin.org",
            "eu.vertcoin.org",
            "vtcpool.co.uk",
            "vtc.kilovolt.co.uk",

        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
