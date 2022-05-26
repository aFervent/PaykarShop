package com.example.paykarshop.models

import ChildCategory

data class ModelCatalog(var sectionId: String, var sectionName: String, var sectionPicture: String, val childCategory: List<ChildCategory> ) {
}