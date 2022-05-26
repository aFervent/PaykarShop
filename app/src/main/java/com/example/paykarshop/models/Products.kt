import com.google.gson.annotations.SerializedName

data class Products (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("basket_quan") val basket_quan : String,
	@SerializedName("wishlist") val wishlist : Boolean,
	@SerializedName("detailText") val detailText : String,
	@SerializedName("hit") val hit : String,
	@SerializedName("picture") val picture : String,
	@SerializedName("price") val price : Double,
	@SerializedName("discountPrice") val discountPrice : Double,
	@SerializedName("baseUnit") val baseUnit : String,
	@SerializedName("discount") val discount : String
)