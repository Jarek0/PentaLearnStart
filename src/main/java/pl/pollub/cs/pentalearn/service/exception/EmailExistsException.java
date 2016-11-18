/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pollub.cs.pentalearn.service.exception;


@SuppressWarnings("serial")
public class EmailExistsException extends Exception{

    public EmailExistsException(final String message) {
        super(message);
    }

}
