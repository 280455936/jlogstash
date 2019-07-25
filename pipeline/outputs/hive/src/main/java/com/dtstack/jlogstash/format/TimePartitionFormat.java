/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.jlogstash.format;


import com.dtstack.jlogstash.format.util.DateUtil;

import java.util.Date;

/**
 * @author toutian
 */

public class TimePartitionFormat {

    private static PartitionEnum partitionEnum;

    private static TimePartitionFormat timePartitionFormat = new TimePartitionFormat();

    public static TimePartitionFormat getInstance(PartitionEnum pe) {
        partitionEnum = pe;
        return timePartitionFormat;
    }

    public static TimePartitionFormat getInstance(String peStr) {
        if (PartitionEnum.DAY.name().equalsIgnoreCase(peStr)) {
            partitionEnum = PartitionEnum.DAY;
        } else if (PartitionEnum.HOUR.name().equalsIgnoreCase(peStr)) {
            partitionEnum = PartitionEnum.HOUR;
        } else {
            throw new UnsupportedOperationException("partitionEnum=" + peStr + " is undefined!");
        }
        return timePartitionFormat;
    }

    private TimePartitionFormat() {
    }

    public String currentTime() {
        if (PartitionEnum.DAY == partitionEnum) {
            return DateUtil.getDayFormatter().format(new Date());
        } else if (PartitionEnum.HOUR == partitionEnum) {
            return DateUtil.getHourFormatter().format(new Date());
        }

        throw new UnsupportedOperationException("partitionEnum=" + partitionEnum + " is undefined!");
    }

    enum PartitionEnum {
        DAY, HOUR
    }

}
