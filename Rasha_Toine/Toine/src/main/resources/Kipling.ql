form Kipling {                                                                          
    

        

    q1  :   "Can V.Z. keep his head when all about him\\n Are losing theirs and blaming it on him?" boolean 
   




    if ( q1 ) {

        q2  :   "Can he trust himself when all men doubt him,\\nBut make allowance for their doubting too?" boolean 
        
        if ( q2 ) {
    
            q3  :   "Can he wait and not be tired by waiting?"           boolean 
    
            if ( q3 )  {
            
                positive : "Rudyard Kipling would say: " string( 
                    "<html>                                         "+
                    "   <h2>                                        "+
                    "       <code>                                  "+
                    "           His is the Earth<br/>               "+
                    "           and everything that’s in it,<br/>   "+
                    "           And — which is more —               "+
                    "       </code><br/>                            "+ 
                    "       he’ll be a Man!<br/>                    "+
                    "       <br/>                                   "+
                    "       <i>Rudyard Kipling</i>                  "+
                    "   </h2>                                       "+
                    "</html>"  
                )
            }
        }
    }
    poem:
    "(‘Brother Square-Toes’ — Rewards and Fairies)" string (
    
"<html> If you can keep your head when all about you             <br/>
            Are losing theirs and blaming it on you,             <br/>
        If you can trust yourself when all men doubt you,        <br/>
            But make allowance for their doubting too;           <br/>
        If you can wait and not be tired by waiting,             <br/>
            Or being lied about, don’t deal in lies,             <br/>
        Or being hated, don’t give way to hating,                <br/>
            And yet don’t look too good, nor talk too wise:      <br/>
        ......
                If you can talk with crowds and keep your virtue,        <br/>
            Or walk with Kings—nor lose the common touch,        <br/>
        If neither foes nor loving friends can hurt you,         <br/>
            If all men count with you, but none too much;        <br/>
        If you can fill the unforgiving minute                   <br/>
            With sixty seconds’ worth of distance run,           <br/>
        Yours is the Earth and everything that’s in it,          <br/>
            And—which is more—you’ll be a Man, my son!           <br/>
       </html>
")
    
}
