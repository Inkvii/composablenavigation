package verzich.composablenavigation

enum class NavigationEnum(val target: String) {
	MAIN_MENU("main menu"),
	PLACEHOLDER("placeholder"),
	MANAGEMENT("management"),
	COUNTER("counter")
	;

	override fun toString(): String {
		return target
	}


}