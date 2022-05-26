import com.google.gson.annotations.SerializedName


data class ChildCategory (

	@SerializedName("sectionId") val sectionId : Int,
	@SerializedName("sectionName") val sectionName : String,
	@SerializedName("sectionPicture") val sectionPicture : String,
	@SerializedName("child") val child : List<Child>
)