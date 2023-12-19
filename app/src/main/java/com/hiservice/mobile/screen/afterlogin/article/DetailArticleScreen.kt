package com.hiservice.mobile.screen.afterlogin.article

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hiservice.mobile.R
import com.hiservice.mobile.ViewModelFactory
import com.hiservice.mobile.components.TopHeadBar
import com.hiservice.mobile.data.injection.Injection
import com.hiservice.mobile.ui.state.UiState
import com.hiservice.mobile.ui.theme.HiServiceTheme
import okhttp3.internal.threadFactory

@Composable
fun DetailArticleScreen(
    articleID: Int,
    viewModel: DetailArticleViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(LocalContext.current)
        )
    ),
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let{ uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getArticleById(articleID)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailArticleContent(
                    articleImg = data.articleDatas.articleImg,
                    articleTitle = data.articleDatas.articleTitle,
                    publisherImg = data.articleDatas.publisherImg,
                    publisherName = data.articleDatas.publisherName,
                    publishDate = data.articleDatas.publishDate,
                    articleDesc = data.articleDatas.articleDesc,
                    onBackClick = { navigateBack}
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun DetailArticleContent(
    @DrawableRes articleImg: Int,
    articleTitle: String,
    @DrawableRes publisherImg: Int,
    publisherName: String,
    publishDate: String,
    articleDesc: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    val scrollState = rememberScrollState()
    Column(modifier = modifier.fillMaxSize()) {
        TopHeadBar(text = "Article", onClick = {

        }, isBack = true)
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .padding(start = 26.dp, end = 26.dp, bottom = 26.dp)
        ){
            //it's Image of article
            Image(
                painter = painterResource(articleImg),
                contentDescription = "Article Image",
                modifier = modifier
                    .height(190.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.height(16.dp))
            //it's title of article
            Text(
                text = articleTitle,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = modifier.height(16.dp))
            //it's publisher identity
            Row {
                Image(
                    painter = painterResource(publisherImg),
                    contentDescription = "Publisher Image",
                    modifier = modifier
                        .height(50.dp)
                        .width(50.dp)
                )
                Spacer(modifier = modifier.width(12.dp))
                Column{
                    Text(
                        text = publisherName,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row{
                        Text(
                            text = "Publish Date : ",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                        Text(
                            text = publishDate,
                            fontSize = 12.sp
                        )
                    }
                }
            }
            Spacer(modifier = modifier.height(16.dp))
            //it's desc of article
            Text(text = articleDesc)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailArticleContentPreview() {
    HiServiceTheme {
        DetailArticleContent(
            articleImg = R.drawable.imgarticle_ganti_oli,
            articleTitle = "Mengapa Harus Ganti Oli Motor Rutin? Ini Penjelasannya!",
            publisherImg = R.drawable.logo_adirafinance,
            publisherName = "Adira Finance",
            publishDate = "19 Sep 2023",
            articleDesc = "Pentingnya mengganti oli motor secara teratur adalah hal yang tidak boleh diabaikan oleh para pemilik kendaraan bermotor. Pelumas motor memiliki peran yang sangat vital dalam menjaga performa dan umur mesin. Dalam artikel ini, kita akan membahas secara mendalam tentang kapan waktu yang tepat untuk mengganti pelumas motor serta beberapa tips penting terkait proses penggantian pelumas mesin agar kendaraan Sahabat tetap optimal.\n" +
                    "\n" +
                    "1. Mengapa Mengganti Oli Motor?\n" +
                    "\n" +
                    "Pelumas mesin motor berfungsi sebagai pelumas utama bagi mesin kendaraan. Fungsinya bukan hanya untuk mengurangi gesekan antar komponen dalam mesin, tetapi juga untuk mendinginkan mesin, mencegah karat, membersihkan kotoran, dan melindungi dari keausan. Seiring berjalannya waktu, kualitas oli akan menurun karena paparan panas dan penggunaan berulang. Inilah mengapa mengganti oli secara berkala sangat penting.\n" +
                    "\n" +
                    "2. Kapan Waktunya Mengganti Oli?\n" +
                    "\n" +
                    "Pertanyaan umum yang sering diajukan adalah seberapa sering kita harus mengganti oli motor. Jawabannya tergantung pada beberapa faktor, seperti jenis oli yang digunakan, jenis kendaraan, gaya mengemudi, dan kondisi lingkungan tempat kendaraan digunakan. Umumnya, perubahan oli motor dilakukan setiap 5.000 - 7.500 kilometer atau 3-6 bulan sekali, tergantung mana yang tercapai lebih dahulu.\n" +
                    "\n" +
                    "Namun, sangat penting untuk memeriksa manual kendaraan Sahabat. Pabrikan kendaraan biasanya memberikan rekomendasi yang sangat akurat tentang jadwal penggantian oli yang ideal untuk model tertentu. Mengikuti panduan pabrikan adalah langkah pertama yang baik dalam menjaga mesin kendaraan.\n" +
                    "\n" +
                    "Tanda-tanda Waktu Mengganti Oli\n" +
                    "\n" +
                    "Selain mengandalkan jadwal penggantian oli yang direkomendasikan, ada beberapa tanda yang bisa menjadi petunjuk bahwa waktunya mengganti oli. Beberapa tanda ini termasuk:\n" +
                    "\n" +
                    "1. Warna Oli yang Kusam\n" +
                    "\n" +
                    "Oli segar biasanya memiliki warna kuning kecoklatan yang jernih. Jika Sahabat memeriksa tongkat ukur oli dan melihat warna yang sangat gelap atau kusam, ini adalah tanda bahwa oli sudah kotor dan perlu diganti.\n" +
                    "\n" +
                    "2. Bau yang Aneh\n" +
                    "\n" +
                    "Jika Sahabat merasakan bau yang tidak biasa ketika mesin menyala, ini bisa menjadi tanda bahwa oli sudah terlalu kotor dan sudah waktunya untuk penggantian. Bau yang aneh bisa mengindikasikan adanya pembakaran atau kontaminasi dalam sistem pelumasan.\n" +
                    "\n" +
                    "3. Suara Mesin yang Berisik\n" +
                    "\n" +
                    "Oli yang kotor tidak akan melumasi dengan baik, sehingga bisa menyebabkan komponen dalam mesin bersentuhan secara kasar. Ini dapat menghasilkan suara mesin yang lebih berisik daripada biasanya.\n" +
                    "\n" +
                    "4. Konsumsi Oli yang Tinggi\n" +
                    "\n" +
                    "Jika terus-menerus harus menambahkan lebih banyak oli daripada biasanya dalam waktu yang singkat, ini bisa menjadi tanda bahwa oli  kehilangan kemampuannya untuk menjaga pelumasan yang baik.\n" +
                    "\n" +
                    "5. Kinerja Mesin Menurun\n" +
                    "\n" +
                    "Jika Sahabat merasa kendaraan motor kurang responsif atau tidak memiliki akselerasi yang baik seperti sebelumnya, oli yang kotor bisa menjadi penyebabnya. Oli yang baik sangat penting bagi kinerja optimal mesin.\n" +
                    "\n" +
                    "Oli yang sudah terlalu lama digunakan akan kehilangan kemampuan pelumasannya dan bisa menyebabkan masalah yang merugikan.\n" +
                    "\n" +
                    "Berikut adalah beberapa dampak yang dapat terjadi ketika telat ganti oli motor\n" +
                    "\n" +
                    "1. Peningkatan Gesekan Berlebihan\n" +
                    "\n" +
                    "Oli berfungsi untuk melumasi komponen dalam mesin agar gesekan antara komponen tersebut berkurang. Jika oli sudah kotor dan terdegradasi, pelumasan yang efektif tidak dapat terjadi. Ini berarti mesin akan mengalami gesekan berlebihan, yang dapat menyebabkan keausan yang lebih cepat pada komponen mesin seperti piston, camshaft, dan bearing.\n" +
                    "\n" +
                    "2. Penyumbatan\n" +
                    "\n" +
                    "Seiring waktu, oli akan mengumpulkan kotoran, endapan, dan partikel-partikel kecil. Jika oli tidak diganti secara berkala, kotoran ini dapat mengendap di berbagai bagian mesin. Ini bisa menyebabkan penyumbatan jalur pelumasan yang kritis, yang pada gilirannya mengganggu peredaran oli dan bisa menyebabkan kerusakan serius pada mesin.\n" +
                    "\n" +
                    "3. Peningkatan Suhu Mesin\n" +
                    "\n" +
                    "Oli juga berfungsi sebagai pengatur suhu mesin. Oli yang sudah terdegradasi tidak dapat mengatur suhu mesin dengan efektif. Ini dapat menyebabkan peningkatan suhu mesin, yang bisa mengakibatkan overheating. Overheating dapat merusak komponen mesin dan bahkan menyebabkan mesin mati.\n" +
                    "\n" +
                    "4. Kerusakan Lebih Parah dan Biaya Perbaikan Tinggi\n" +
                    "\n" +
                    "Jika Sahabat terus mengabaikan ganti oli, dampak-dampak yang telah disebutkan di atas dapat menyebabkan kerusakan yang lebih serius pada mesin. Perbaikan mesin yang rusak akibat masalah oli bisa sangat mahal, terutama jika harus mengganti komponen yang rusak.\n" +
                    "\n" +
                    "5. Umur Mesin Lebih Pendek\n" +
                    "Oli yang baik dan teratur penggantiannya adalah salah satu faktor utama yang mempengaruhi umur mesin. Jika terus-menerus telat ganti oli, mesin motor memiliki peluang yang lebih tinggi untuk mengalami keausan lebih cepat dan kerusakan serius. Ini dapat mereduksi masa pakai mesin secara keseluruhan.\n" +
                    "\n" +
                    "Tips untuk Penggantian Oli yang Optimal\n" +
                    "\n" +
                    "Sekarang, setelah kita membahas kapan waktu yang tepat untuk mengganti oli, berikut beberapa tips penting untuk memastikan penggantian oli berjalan dengan optimal:\n" +
                    "\n" +
                    "1. Gunakan Pelumas Berkualitas\n" +
                    "\n" +
                    "Pilih pelumas mesin yang sesuai dengan spesifikasi yang direkomendasikan oleh pabrikan kendaraan. Jangan mengorbankan kualitas demi harga yang lebih murah, karena oli yang buruk bisa merusak mesin.\n" +
                    "\n" +
                    "2. Ganti Filter\n" +
                    "\n" +
                    "Selama proses penggantian pelumas, pastikan untuk juga mengganti filter. Filter oli membantu menyaring kotoran dan partikel kecil yang dapat merusak mesin jika tidak dihilangkan.\n" +
                    "\n" +
                    "3. Periksa Tanggal Kadaluarsa Oli\n" +
                    "\n" +
                    "Pelumas mesin juga memiliki batas umur simpan, terlepas dari berapa banyak Sahabat telah menggunakannya. Periksa tanggal kadaluarsa pada kemasan pelumas sebelum menggunakannya.\n" +
                    "\n" +
                    "4. Pahami Lingkungan Penggunaan\n" +
                    "\n" +
                    "Jika Sahabat sering mengemudi dalam kondisi berat, seperti kemacetan lalu lintas yang parah atau perjalanan panjang, Sahabat mungkin perlu mengganti oli lebih sering daripada yang direkomendasikan.\n" +
                    "\n" +
                    "5. Simpan Catatan\n" +
                    "\n" +
                    "Buatlah catatan setiap kali mengganti oli. Ini akan membantu untuk mengikuti jadwal dengan lebih baik dan memberikan bukti perawatan kendaraan jika berencana untuk menjualnya di masa depan.\n" +
                    "\n" +
                    "Dengan mengikuti tips diatas tentu kendaraan motor Sahabat akan lebih terjaga dan optimal tentunya. Maka dari itu pentingnya merawat kendaraan kita dengan rutin ganti oli motor agar terhindar dari kerusakan.",
            onBackClick = {}
        )
    }
}