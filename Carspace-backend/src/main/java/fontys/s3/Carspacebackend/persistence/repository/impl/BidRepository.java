package fontys.s3.Carspacebackend.persistence.repository.impl;

import fontys.s3.Carspacebackend.business.interfaces.IBidRepository;
import fontys.s3.Carspacebackend.converters.BidConverter;
import fontys.s3.Carspacebackend.domain.Bid;
import fontys.s3.Carspacebackend.exception.ResourceNotFoundException;
import fontys.s3.Carspacebackend.persistence.Entity.AuctionEntity;
import fontys.s3.Carspacebackend.persistence.Entity.BidEntity;
import fontys.s3.Carspacebackend.persistence.Entity.UserEntity;
import fontys.s3.Carspacebackend.persistence.repository.IJPAAuctionRepository;
import fontys.s3.Carspacebackend.persistence.repository.IJPABidRepository;
import fontys.s3.Carspacebackend.persistence.repository.IJPAUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class BidRepository implements IBidRepository {
    private IJPABidRepository bidRepository;
    private final IJPAUserRepository userRepository;
    private final IJPAAuctionRepository auctionRepository;

    @Override
    public Long saveBid(Bid b, Long auctionId, Long userId){
        Optional<AuctionEntity> auction = auctionRepository.findById(auctionId);
        if(auction.isEmpty()){
            throw new ResourceNotFoundException("Auction", "id", auctionId);
        }
        Optional<UserEntity> bidder = userRepository.findById(userId);
        if(bidder.isEmpty()){
            throw new ResourceNotFoundException("User", "id", userId);
        }
        BidEntity bid = BidConverter.convertToEntity(b);
        bid.setAuction(auction.get());
        bid.setBidder(bidder.get());
        bidRepository.save(bid);

        return bid.getId();
    }
}