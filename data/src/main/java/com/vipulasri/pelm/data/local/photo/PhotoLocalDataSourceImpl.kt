package com.vipulasri.pelm.data.local.photo

import com.vipulasri.pelm.domain.SafeResult
import com.vipulasri.pelm.domain.model.Photo

/**
 * Created by Vipul Asri on 30/01/22.
 */

class PhotoLocalDataSourceImpl : PhotoLocalDataSource {

    private val photos = listOf(
        Photo(
            thumbnail = "https://i.picsum.photos/id/1000/500/500.jpg?hmac=suY41yRTc6evFAAMLDanMbqOJx6MDVQ_Gv-FNquR9cc",
            url = "https://i.picsum.photos/id/1000/5626/3635.jpg?hmac=qWh065Fr_M8Oa3sNsdDL8ngWXv2Jb-EE49ZIn6c0P-g"
        ),
        Photo(
            thumbnail = "https://i.picsum.photos/id/1001/500/500.jpg?hmac=u-024f0NoeQ0OX_zZmM_uitzrTCy9Vlk_uyEPfExwfw",
            url = "https://i.picsum.photos/id/1001/5616/3744.jpg?hmac=38lkvX7tHXmlNbI0HzZbtkJ6_wpWyqvkX4Ty6vYElZE"
        ),
        Photo(
            thumbnail = "https://i.picsum.photos/id/10/500/500.jpg?hmac=UjAKeZC8vJuIOAgP6KI_BPcrxd8YMqTpRVKtbMexYrY",
            url = "https://i.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68"
        ),
        Photo(
            thumbnail = "https://i.picsum.photos/id/1002/500/500.jpg?hmac=PoR97MywVbxXRCsUdyiLttHcb5ZGmhrQGUnfCUbkU4w",
            url = "https://i.picsum.photos/id/1002/4312/2868.jpg?hmac=5LlLE-NY9oMnmIQp7ms6IfdvSUQOzP_O3DPMWmyNxwo"
        ),
        Photo(
            thumbnail = "https://i.picsum.photos/id/1003/500/500.jpg?hmac=amgOHySIjhBr5617LUggmjguSC3r0bCzsNHfRxS7cGY",
            "https://i.picsum.photos/id/1003/1181/1772.jpg?hmac=oN9fHMXiqe9Zq2RM6XT-RVZkojgPnECWwyEF1RvvTZk"
        ),
        Photo(
            thumbnail = "https://i.picsum.photos/id/1010/500/500.jpg?hmac=ixEkfOcyKhj0Oq_JYtMILWVZ4p-UEAv5jMuBV64KJWI",
            url = "https://i.picsum.photos/id/1010/5184/3456.jpg?hmac=7SE0MNAloXpJXDxio2nvoshUx9roGIJ_5pZej6qdxXs"
        ),
        Photo(
            thumbnail = "https://i.picsum.photos/id/1011/500/500.jpg?hmac=YN3oCpwpniYpKEclMAlUd1vWTmlpeh6BUdpODrFAINc",
            url = "https://i.picsum.photos/id/1011/5472/3648.jpg?hmac=Koo9845x2akkVzVFX3xxAc9BCkeGYA9VRVfLE4f0Zzk"
        ),
        Photo(
            thumbnail = "https://i.picsum.photos/id/1015/500/500.jpg?hmac=LNni84jXVOXdvxYPr-DoeAxRSQnnd-9Sf_-CunUKGYI",
            url = "https://i.picsum.photos/id/1015/6000/4000.jpg?hmac=aHjb0fRa1t14DTIEBcoC12c5rAXOSwnVlaA5ujxPQ0I"
        ),
        Photo(
            thumbnail = "https://i.picsum.photos/id/102/500/500.jpg?hmac=B0KI1e2bgYjHUk7tfMIQ_w6Q3TbnQSYJDrWuNNHx_-o",
            url = "https://i.picsum.photos/id/102/4320/3240.jpg?hmac=ico2KysoswVG8E8r550V_afIWN963F6ygTVrqHeHeRc"
        ),
        Photo(
            thumbnail = "https://i.picsum.photos/id/1024/500/500.jpg?hmac=dLr0aDZbynsr8ZvWIWsQSU54QOEPaS6YGRtg5fjatow",
            url = "https://i.picsum.photos/id/1024/1920/1280.jpg?hmac=-PIpG7j_fRwN8Qtfnsc3M8-kC3yb0XYOBfVzlPSuVII"
        )
    )

    override fun getPhotos(): SafeResult<List<Photo>> {
        return SafeResult.Success(photos)
    }

}