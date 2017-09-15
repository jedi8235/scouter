/*
 *  Copyright 2015 the original author or authors.
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package scouterx.model.summary;

import lombok.Data;

import java.util.Map;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 9. 14.
 */
@Data
public class Summary<T extends SummaryItem> {
    private int summaryKey;
    private Map<Integer, T> itemMap;

    public void merge(T newItem) {
        T reservedItem = itemMap.get(newItem.getSummaryKey());
        if(reservedItem == null) {
            addItem(newItem);
        } else {
            mergeItem(newItem, reservedItem);
        }
    }

    private void mergeItem(T newItem, T reservedItem) {
        reservedItem.merge(newItem);
    }

    private void addItem(T newItem) {
        itemMap.put(newItem.getSummaryKey(), newItem);
    }
}
