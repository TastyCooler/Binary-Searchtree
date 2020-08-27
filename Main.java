class Main {
  public static void main( String[] args )
    {
        firstTreeTest();
        //secondTreeTest();
    }

    public static void firstTreeTest()
    {
      char[] c = {'a','b','y','x'};
        String s = "" ;

        CharacterSearchTree hal = new CharacterSearchTree();
       // hal.add( 'a',3, "30" );
        hal.add( 'z', 20, "o");
        hal.add( 'v', 30, "o");
        hal.add( 'w', 80, "o");
        hal.add( 'm', 60, "o");
        hal.add( 'y', 40, "o");
        hal.add( 'a', 95, "o");
        hal.add( 'u', 45, "o");
       // hal.showPreOrder();
        
       // System.out.println(hal.test1());
        //System.out.println(hal.test2());
        System.out.println(hal.test3());
       //System.out.println( hal.height() );
       //System.out.println( hal.countCharacters());
       //System.out.println(hal.minimum());
        //System.out.println(hal.hasOnlyCompleteNode());
        //System.out.println(hal.containsCharacter('e'));

        System.out.println( "binary tree: " );
        System.out.println( "--------------------------" );
        hal.show();
        System.out.println();
        HuffmanCoding coding = new HuffmanCoding( hal.toArray() );
        System.out.println( "code table: " );
        System.out.println( "--------------------------" );
        coding.showCodeTable();

        //System.out.println( hal.longestCode());
        
        System.out.println();
        System.out.println( "binary tree with codes: " );
        System.out.println( "--------------------------" );
        hal.show();
        String codeOfHal = "";
        /*for ( int i = 0; i < s.length() ; i++ )
        {
            codeOfHal += hal.getCode( s.charAt( i ) );
        } */
        System.out.println( "bit code: " );
        System.out.println( "--------------------------" );
        System.out.println( codeOfHal );
        
        }

    public static void secondTreeTest()
    {
        String s = "Die Würde des Menschen ist unantastbar. " +
            "Sie zu achten und zu schätzen ist Verpflichtung aller staatlichen Gewalt. " +
            "Das Deutsche Volk bekennt sich darum zu unverletzlichen und unveruerlichen " +
            "Menschenrechten als Grundlage jeder menschlichen Gemeinschaft, des Friedens und " +
            "der Gerechtigkeit in der Welt. ";

        CharacterSearchTree gg = new CharacterSearchTree();
        for ( int i = 0; i < s.length() ; i++ )
        {
            gg.add( s.charAt( i ) );
        }
        System.out.println(gg.hasOnlyCompleteNode());
        System.out.println( "binary tree: " );
        System.out.println( "--------------------------" );
        gg.show();
        System.out.println();
        System.out.println( "binary tree (toArray): " );
        System.out.println( "--------------------------" );
        HuffmanTriple[] hmts = gg.toArray();
        for ( HuffmanTriple hmt : hmts )
        {
            System.out.print( hmt.getToken() + " " );
        }
        System.out.println(); System.out.println();
        HuffmanCoding coding = new HuffmanCoding( gg.toArray() );
        System.out.println( "code table: " );
        System.out.println( "--------------------------" );
        coding.showCodeTable();
        System.out.println();
        System.out.println( "binary tree with codes: " );
        System.out.println( "--------------------------" );
        gg.show();
        String codeOfGG = "";
        for ( int i = 0; i < s.length() ; i++ )
        {
            codeOfGG += gg.getCode( s.charAt( i ) );
        }
        System.out.println( "bit code: " );
        System.out.println( "--------------------------" );
        for ( int i = 0; i < codeOfGG.length() ; i++ )
        {
            System.out.print( codeOfGG.charAt( i ) );
            if ( (i+1) % 80 == 0 )
            {
                System.out.println();
            }
        }
        System.out.println();
        System.out.println( "input chars: " + s.length() );
        System.out.println( "input bits: " + s.length() * 8 );
        System.out.println( "output bits: " + codeOfGG.length() );
    }
}