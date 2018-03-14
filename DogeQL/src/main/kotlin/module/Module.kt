package module

import org.kodein.Kodein
import org.kodein.generic.bind
import org.kodein.generic.instance
import org.kodein.generic.provider
import org.kodein.newInstance

class Module {
    val kodein = Kodein {
//        //                bind() from provider { Person() }
////                bind<Person>() with provider { Person() }
//        bind<Employee>() with provider {  Employee(instance()) }
////                bind<Company>() with  provider { Company(instance()) }
//    }

    //            val company: Company by kodein.instance()
//    val company by kodein.newInstance { Company(instance()) }
    }
}