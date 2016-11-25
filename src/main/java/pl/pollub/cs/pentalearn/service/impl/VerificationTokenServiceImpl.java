package pl.pollub.cs.pentalearn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentalearn.domain.VerificationToken;
import pl.pollub.cs.pentalearn.repository.VerificationTokenRepository;
import pl.pollub.cs.pentalearn.service.VerificationTokenService;
import pl.pollub.cs.pentalearn.service.exception.NoSuchObjectException;
import pl.pollub.cs.pentalearn.service.exception.TableIsEmptyException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;


    @Inject
    public VerificationTokenServiceImpl(final VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    @Transactional
    public VerificationToken save(@NotNull @Valid final VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    @Transactional
    public List<VerificationToken> getAllVerificationTokens() throws TableIsEmptyException {
        List<VerificationToken> tokens = verificationTokenRepository.findAll();
        if (tokens.size() == 0)
            throw new TableIsEmptyException("vtoken");
        return tokens;
    }

    @Override
    @Transactional
    public VerificationToken update(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    @Transactional
    public VerificationToken delete(VerificationToken token) {
        verificationTokenRepository.delete(token);
        return token;
    }

    @Override
    @Transactional
    public VerificationToken getById(Long id) throws NoSuchObjectException {
        VerificationToken token = verificationTokenRepository.findOne(id);
        if (token == null) throw new NoSuchObjectException(id);
        return token;
    }


    @Override
    public VerificationToken getByToken(String token) throws NoSuchObjectException {
        VerificationToken vtoken = verificationTokenRepository.findByToken(token);
        if (vtoken == null) throw new NoSuchObjectException(token);
        return vtoken;
    }

    @Override
    public VerificationToken generateNewVerificationToken(String existingToken) {
        return new VerificationToken(UUID.randomUUID().toString(), (verificationTokenRepository.findByToken(existingToken)).getUser());
    }
}
