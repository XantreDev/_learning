sealed class Event {
    data class Start(val x: Int) : Event()
    data class End(val x: Int) : Event()
    data class Point(val x: Int, val index: Int) : Event()
}

fun getX(item: Event): Int {
    return when (item) {
        is Event.Start -> item.x
        is Event.End -> item.x
        is Event.Point -> item.x
    }
}

fun getPriority(item: Event): Int {
    return when (item) {
        is Event.Start -> 0
        is Event.Point -> 1
        is Event.End -> 2
    }
}

class EventComparator : Comparator<Event> {
    override fun compare(a: Event, b: Event): Int {
        if (getX(a) == getX(b)) {
            return getPriority(a).compareTo(getPriority(b))
        }

        return getX(a).compareTo(getX(b))
    }
}


fun main() {
    val (range_amount) = (readLine()?.split(" ") ?: listOf()).map { it.toInt() }
    val ranges = (1..range_amount).map { (readLine()?.split(" ") ?: listOf()).map { it.toInt() } }
    val points = (readLine()?.split(" ") ?: listOf()).map { it.toInt() }

    val ranges_arr = mutableListOf<Event>()
    for ((a, b) in ranges) {
        ranges_arr.add(Event.Start(Math.min(a, b), ))
        ranges_arr.add(Event.End(Math.max(a, b), ))
    }
    for ((index, point) in points.withIndex()) {
        ranges_arr.add(Event.Point(point, index))
    }
    ranges_arr.sortWith(EventComparator())
    
    var current_amount_of_ranges = 0
    val result = MutableList(points.size, { 0 })
    
    for (item in ranges_arr) {
        when (item) {
            is Event.Start -> {
               current_amount_of_ranges++ 
            }
            is Event.End -> {
                current_amount_of_ranges--
            }
            is Event.Point -> {
                result[item.index] = current_amount_of_ranges
            }
        }
    }
    
    println(result.joinToString(separator=" "))
}
