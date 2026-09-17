package com.ope.inv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OperacionInventarioApplication{

	public static void main( String[] args ){
		SpringApplication.run( OperacionInventarioApplication.class, args );
		/*
		Integer[] nums1 = {1, 2, 3, 0, 0, 0};
		Integer m = 3;

	    Integer[] nums2 = {2, 5, 6};
	    Integer n = 3;

	    merge(nums1, m, nums2, n);

	    for (Integer x : nums1) {
	        System.out.print( x + " " );
	    }
	    
	    
	    
	    int[] numeracion = { 1, 2, 3, 4, 5, 6, 7 } ;
        int k = 3 ;

        rotate( numeracion, k ) ;

        for( int v : numeracion ) {
            System.out.print( " " + v ) ;
        }
        
        System.out.print( " " + reverseStr( "abcdef", 2 ) ) ;
	}
	
	private static String reverseStr( String s, int k ) {
		char[] arr = s.toCharArray();
        int n = arr.length;

        for (int i = 0; i < n; i += 2 * k) {
            int left = i;
            int right = Math.min(i + k - 1, n - 1);
            
            reverse(arr, left, right);
        }
        return new String(arr);
	}
	
	private static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
	
	public static void merge(Integer[] nums1, Integer m, Integer[] nums2, Integer n) {

		Integer c = m - 1;        // índice final de nums1 válido
		Integer d = n - 1;        // índice final de nums2
		Integer e = m + n - 1;    // índice final del arreglo nums1 completo

	    // Mezclar desde el final
		for( Integer x = 0 ; ( c >= 0 && d >= 0 ) ; ){
            if( nums1[ c ] > nums2[ d ] ){
                nums1[ e ] = nums1[ c ] ;
                c-- ;
            }
            else{
                nums1[ e ] = nums2[ d ] ;
                d-- ;
            }
            e--;
        }

	    // Si quedan elementos en nums2, copiarlos
	    while( d >= 0 ) {
	        nums1[e] = nums2[d];
	        d--;
	        e--;
	    }
	}
	
	
	public static void rotate( int[] nums, int k ) {
        int f = nums.length - 1, i = 0, a = k ;
        
        while( i <= f ){
            int num = nums[ f ] ;
            int num2 = nums[ i ] ;

            nums[ i ] = num ;
            nums[ f ] = num2 ;

            f-- ;
            i++ ;
        }        

        i = 0 ;
        a = k - 1 ;
        while( i <= a ){
            int num = nums[ a ] ;
            int num2 = nums[ i ] ;

            nums[ i ] = num ;
            nums[ a ] = num2 ;

            a-- ;
            i++ ;
        }
        
        
        f = nums.length - 1 ;
        while( k <= f ){
            int ini = nums[ k ] ;
            int fin = nums[ f ] ;

            nums[ k ] = fin ;
            nums[ f ] = ini ;

            k++ ;
            f-- ;
        }
            */
    }
}