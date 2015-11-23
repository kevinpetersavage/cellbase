/*
 * Copyright 2015 OpenCB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opencb.cellbase.core.common.regulatory;

// Generated Jun 5, 2012 6:41:13 PM by Hibernate Tools 3.4.0.CR1


import org.opencb.cellbase.core.common.core.Gene;
import org.opencb.cellbase.core.common.core.Transcript;

/**
 * MirnaToGene generated by hbm2java.
 */
public class MirnaToGene implements java.io.Serializable {

    private int mirnaToGeneId;
    private MirnaGene mirnaGene;
    private Transcript transcript;
    private Gene gene;

    public MirnaToGene() {
    }

    public MirnaToGene(int mirnaToGeneId, MirnaGene mirnaGene, Transcript transcript, Gene gene) {
        this.mirnaToGeneId = mirnaToGeneId;
        this.mirnaGene = mirnaGene;
        this.transcript = transcript;
        this.gene = gene;
    }

    public int getMirnaToGeneId() {
        return this.mirnaToGeneId;
    }

    public void setMirnaToGeneId(int mirnaToGeneId) {
        this.mirnaToGeneId = mirnaToGeneId;
    }

    public MirnaGene getMirnaGene() {
        return this.mirnaGene;
    }

    public void setMirnaGene(MirnaGene mirnaGene) {
        this.mirnaGene = mirnaGene;
    }

    public Transcript getTranscript() {
        return this.transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public Gene getGene() {
        return this.gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

}
