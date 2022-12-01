fun day1(input:List<String>,maxNumber:Int):Int{
    val data=ArrayList<Int>()
    data.add(0)
    var i=0

    for(j in input){
        if(j==""){
            data.add(0)
            i++
        }else{
            data[i]+=j.toInt()
        }
    }
    //never do this in production =)
    var sumMax=0
    for(j in 1..maxNumber){
        val max=data.max()
        val index=data.indexOf(max)
        data.removeAt(index)
        sumMax+=max
    }
    return sumMax
}
