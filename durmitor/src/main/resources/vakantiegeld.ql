form currencyExample { 
	"Bruto maandsalaris" bruto_maand_salaris: EUR
	"Hele jaar gewerkt?" hele_jaar_gewerkt: boolean
	if(hele_jaar_gewerkt) {
		"Maanden gewerkt" maanden_gewerkt: integer = (12)
	} else {
		"Maanden gewerkt" maanden_gewerkt: integer
	}
	"Vakantiegeld" vakantiegeld: EUR = (bruto_maand_salaris * maanden_gewerkt * 0.08)
}