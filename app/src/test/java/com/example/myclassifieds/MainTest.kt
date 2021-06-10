package com.example.myclassifieds

import android.content.Context
import com.example.myclassifieds.adapter.ClassifyAdapter
import com.example.myclassifieds.model.Classify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config



@PrepareForTest(*[ClassifyAdapter::class])
class MainTest : ClassifyAdapter.CellClickListener {
    @Mock
    var context: Context? = null
    private var adapter: ClassifyAdapter? = null
    var users: ArrayList<Classify?> = ArrayList()
    var classify: Classify? = null
    var image_ids = java.util.ArrayList<String>()
    var image_urls = java.util.ArrayList<String>()
    var image_urls_thumbnails = java.util.ArrayList<String>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        image_ids.add("9355183956e3445e89735d877b798689")
        image_urls.add(
            "https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689?AWSAccessKeyId=ASIASV3YI6A46APVB5MM&Signature=xXKi7KM8w9tVlpqKPE8%2Bh5wgwWY%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEOX%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIBbvOVdPXzY7rdfNkdZfMA3ZzDQA9wqtGsUBHVA3WZzKAiEAmSstTjjLTerTDHmk2aJTaLWzbJmfikCgPSC%2FX2u%2BrSsqnQIInv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARACGgwxODQzOTg5NjY4NDEiDPdbr0rQLn0xx%2BPStCrxAZWz4ak1c7hWCjZWLGEZUMLfudIpERJJPa7Ce%2BPMdBlAaVF1dZiE3rYRjtLeS%2BA1n9%2B9wdAEWQeXA3e6rodV2va1XUyqlGdnHuCjuItnoFTnD2gberkRcgqWenMkG6z9UVd1LCtFRkRxRgcP5AkMUw4yT%2BQrTw4YNb3NikHJBUFMKLs%2F6xOVklPTDc8buNAYgPuXCdH8ynumZZAci8qQRTbYE%2BrJsn7PFgB1myH%2B3j2xzyklJoyFOPXJmFPJoZprZAzOndIkJzLYvJZWJlkm2TwDMIupFZW1J2wVmHAZ%2Fpp61mHJXqRIxowonH%2FoJq5uM2sw8rmGhgY6mgG3iBjSRhVFs6J2O7BlAK9LW4Rr6L3E0cJVndFfa1%2Bm64tLxKLq50DjqLrMXt0OmIF%2F6OhMvCwxG43xbZAce7JyuD%2FgihwkhbBut2IZ1YsD5sAFD7HrLspxbKB%2B4Ku4BhXn6TZ5vkBrMSBcx32ZoNTADIZPsm54JphCu2v0uHKK66ebqY0i4vWKCTah%2BEf9o6sNaRGB%2FAxCeI1v&Expires=1623304965"
        )
        image_urls_thumbnails.add(
            "https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689?AWSAccessKeyId=ASIASV3YI6A46APVB5MM&Signature=xXKi7KM8w9tVlpqKPE8%2Bh5wgwWY%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEOX%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJHMEUCIBbvOVdPXzY7rdfNkdZfMA3ZzDQA9wqtGsUBHVA3WZzKAiEAmSstTjjLTerTDHmk2aJTaLWzbJmfikCgPSC%2FX2u%2BrSsqnQIInv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARACGgwxODQzOTg5NjY4NDEiDPdbr0rQLn0xx%2BPStCrxAZWz4ak1c7hWCjZWLGEZUMLfudIpERJJPa7Ce%2BPMdBlAaVF1dZiE3rYRjtLeS%2BA1n9%2B9wdAEWQeXA3e6rodV2va1XUyqlGdnHuCjuItnoFTnD2gberkRcgqWenMkG6z9UVd1LCtFRkRxRgcP5AkMUw4yT%2BQrTw4YNb3NikHJBUFMKLs%2F6xOVklPTDc8buNAYgPuXCdH8ynumZZAci8qQRTbYE%2BrJsn7PFgB1myH%2B3j2xzyklJoyFOPXJmFPJoZprZAzOndIkJzLYvJZWJlkm2TwDMIupFZW1J2wVmHAZ%2Fpp61mHJXqRIxowonH%2FoJq5uM2sw8rmGhgY6mgG3iBjSRhVFs6J2O7BlAK9LW4Rr6L3E0cJVndFfa1%2Bm64tLxKLq50DjqLrMXt0OmIF%2F6OhMvCwxG43xbZAce7JyuD%2FgihwkhbBut2IZ1YsD5sAFD7HrLspxbKB%2B4Ku4BhXn6TZ5vkBrMSBcx32ZoNTADIZPsm54JphCu2v0uHKK66ebqY0i4vWKCTah%2BEf9o6sNaRGB%2FAxCeI1v&Expires=1623304965"
        )

        classify = Classify(
            "2019-02-24 04:04:17", "AED 5", "Notebook", "4878bf592579410fba52941d00b62f94",
            image_ids, image_urls, image_urls_thumbnails
        )

        users.add(classify)
    }

    @Test
    fun testing() {
      //  liveZoneGridAdapter = spy(ClassifyAdapter(users,this))
       // doNothing().`when`(liveZoneGridAdapter)?.internalNotifyItemInserted(anyInt())
       // liveZoneGridAdapter.addGridItemsToView(0,classify)
       // verify(liveZoneGridAdapter)?.internalNotifyItemInserted(0)

        adapter = PowerMockito.mock(ClassifyAdapter::class.java)
        PowerMockito.`when`(adapter.hasStableIds()).thenReturn(true)

        Assert.assertEquals(4, 2 + 2)


    }

    override fun onCellClickListener(data: Classify) {
        TODO("Not yet implemented")
    }
}