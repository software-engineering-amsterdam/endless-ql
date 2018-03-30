stylesheet taxOfficeExample {

    page Housing {
        section "Buying" {
            [question hasBoughtHouse [widget checkbox]]* 
        }
        section "Loaning" 
            question hasMaintLoan [widget checkbox]
    }
    page Selling { 
        section "Selling" {
            question hasSoldHouse widget radio("Yes", "No") 
            section "You sold a house" {
                question sellingPrice widget spinbox
                question privateDebt widget spinbox 
            }
        }
    }  
}

stylesheet:     'stylesheet' Id '{'  page*          '}'
page:           'page'       Id '{'  section*       '}'
section:        'section'    Qs '{'  sectionblock*  '}'
sectionblock:   question | section  
question:       [ 'question' Id [widget]]*
widget:         'widget' ('checkbox' | 'radio')



/*                question valueResidue default money {
                    width: 400
                    font: "Arial" 
                    fontsize: 14
                    color: #999999
                    widget spinbox
                 }
 */        
//        default boolean widget radio("Yes", "No")