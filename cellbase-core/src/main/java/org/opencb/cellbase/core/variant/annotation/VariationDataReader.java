package org.opencb.cellbase.core.variant.annotation;

import org.apache.commons.lang.math.NumberUtils;
import org.opencb.biodata.models.variant.Variant;
import org.opencb.cellbase.core.api.VariantDBAdaptor;
import org.opencb.commons.datastore.core.Query;
import org.opencb.commons.datastore.core.QueryOptions;
import org.opencb.commons.io.DataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fjlopez on 11/02/16.
 */
public class VariationDataReader implements DataReader<Variant> {

    private VariantDBAdaptor dbAdaptor;
    private Query query;
    private QueryOptions options;
    private Iterator<Variant> iterator;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int nReadVariants = 0;

    public VariationDataReader(VariantDBAdaptor dbAdaptor, Query query, QueryOptions options) {
        this.dbAdaptor = dbAdaptor;
        this.query = query;
        this.options = options;
    }

    public boolean open() {
        return true;
    }

    public boolean pre() {
        this.iterator = dbAdaptor.iterator(query, options);

        return this.iterator != null;
    }

    /**
     * Performs one read of from a CellBase variation collection.
     *
     * @return  List of variants. It can be expected to contain only one variant.
     */
    public List<Variant> read() {

        Variant variant = null;
        boolean valid = false;
        while (iterator.hasNext() && !valid) {
            variant = iterator.next();
            valid = isValid(variant);
        }
        if (valid) {
            // New variants in the variation collection created during the update of the frequencies may not have
            // the variant type set and this might cause NPE
            if (variant.getType() == null) {
                variant.setType(variant.inferType(variant.getReference(), variant.getAlternate(),
                        variant.getLength()));
            }
            return Collections.singletonList(variant);
        } else {
            return null;
        }
    }

    /**
     * Checks whether a variant is valid.
     *
     * @param variant Variant object to be checked.
     * @return   true/false depending on whether 'variant' does contain valid values. Currently just a simple check of
     * reference/alternate attributes not being numbers is performed to detect cases such as 1:13123123:4:5. Functionality
     * of the method may be improved in the future.
     */
    private boolean isValid(Variant variant) {
        return !(NumberUtils.isNumber(variant.getReference()) || NumberUtils.isNumber(variant.getAlternate()));
    }

    public List<Variant> read(int batchSize) {
        List<Variant> listRecords = new ArrayList<>(batchSize);
        for (int i = 0; i < batchSize; i++) {
            List<Variant> variants = this.read();
            if (variants != null) {
                listRecords.addAll(variants);
                nReadVariants += variants.size();
            } else {
                logger.info("{} variants read", nReadVariants);
                return listRecords;
            }
        }

        logger.info("{} variants read", nReadVariants);
        return listRecords;
    }

    public boolean post() {
        return true;
    }

    public boolean close() {
        return true;
    }

}
