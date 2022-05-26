import com.google.gson.annotations.SerializedName

data class Child (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("picture") val picture : String,
	@SerializedName("products") val products : List<Products>
)