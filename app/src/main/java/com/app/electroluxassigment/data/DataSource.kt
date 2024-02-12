package com.app.electroluxassigment.data

import com.app.electroluxassigment.R
import com.app.electroluxassigment.ui.model.DataInfo

interface IDataSource {
    fun getData(): List<DataInfo>
}

class DataSourceImpl : IDataSource {
    override fun getData(): List<DataInfo> = listOf(
        DataInfo(
            1,
            "Cotton Eco",
            "Cupboard-dries cottons with maximum energy saving.",
            R.drawable.ic_cotton_eco,
            isSelected = false,
            isEnable = true
        ),
        DataInfo(
            2, "Cottons",
            "100% cotton fabrics.",
            R.drawable.ic_cotton_eco,
            isSelected = false,
            isEnable = true
        ),
        DataInfo(
            3,
            "Synthetics",
            "For a mix of mostly synthetic fabrics, like polyester, or polyamide.",
            R.drawable.ic_synthetic,
            isSelected = false,
            isEnable = false
        ),
        DataInfo(
            4,
            "Mixed+",
            "Cotton, cotton-synthetic blends, and synthetics that don’t need ironing.",
            R.drawable.ic_mixed,
            isSelected = false,
            isEnable = true
        ),
        DataInfo(
            5,
            "Delicates", "Viscose, rayon, acrylic, and other blends.",
            R.drawable.ic_delicate,
            isSelected = false,
            isEnable = true
        ),
        DataInfo(
            6,
            "Sports",
            "Athletic clothes made of synthetics like polyester, elastane, or polyamide.",
            R.drawable.ic_sport,
            isSelected = false,
            isEnable = true
        ),
        DataInfo(
            7,
            "Bed linen XL",
            "Up to 2 single and 1 double sets of bedding.",
            R.drawable.ic_bedding,
            isSelected = false,
            isEnable = false
        )
    )
}