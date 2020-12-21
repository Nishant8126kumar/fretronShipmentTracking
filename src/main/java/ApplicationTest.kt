import component.DaggerComponent

fun main()
{
    val component=DaggerComponent.builder().build()
    component.server()
}