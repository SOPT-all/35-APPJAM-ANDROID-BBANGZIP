package org.android.bbangzip.presentation.type

enum class BbangZipLevelType(
    val bbangZipName: String,
    val bbangZipImg: String,
    val bbangZipDescription: String,
    val bbangZipLevelPoint: Int,
) {
    // TODO 결정된 레벨 타입에 따라 String 추출
    LEVEL1(
        bbangZipName = "돗자리",
        bbangZipImg = "https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/ffe8/2a8662f3a23e240506209ff00b716729ef4240b7089129c1209ec06a4513.png",
        bbangZipDescription = "빵집을 시작한지 얼마 안된\n사장님의 첫 빵집이에요",
        bbangZipLevelPoint = 200,
    ),
    LEVEL2(
        bbangZipName = "가판대",
        bbangZipImg = "https://w7.pngwing.com/pngs/355/333/png-transparent-coffee-shop-store-drawing-cartoon-food-stall-shop-shopping-market-thumbnail.png",
        bbangZipDescription = "열심히 노력해서 가판대가 생겼군요\n 사장님의 열정을 응원해요!",
        bbangZipLevelPoint = 300,
    ),
    LEVEL3(
        bbangZipName = "진짜 빵집",
        bbangZipImg = "https://cdn-icons-png.flaticon.com/512/5223/5223875.png",
        bbangZipDescription = "레전드 빵집차렸네 너 쩐다 ㄷㄷ",
        bbangZipLevelPoint = 400,
    ),
}
