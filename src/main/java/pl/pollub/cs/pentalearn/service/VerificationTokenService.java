package pl.pollub.cs.pentalearn.service;


import pl.pollub.cs.pentalearn.domain.VerificationToken;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import java.util.List;

public interface VerificationTokenService {
    VerificationToken save(VerificationToken verificationToken);

    List<VerificationToken> getAllVerificationTokens() throws TableIsEmptyException;

    VerificationToken update(VerificationToken verificationToken);

    VerificationToken delete(VerificationToken role);

    VerificationToken getById(Long id) throws NoSuchObjectException;

    VerificationToken generateNewVerificationToken(String existingToken);

    VerificationToken getByToken(String token) throws NoSuchObjectException;
}
