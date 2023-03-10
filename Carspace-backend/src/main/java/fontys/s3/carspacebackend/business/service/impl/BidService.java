package fontys.s3.carspacebackend.business.service.impl;

import fontys.s3.carspacebackend.business.interfaces.IAuctionRepository;
import fontys.s3.carspacebackend.business.interfaces.IBidRepository;
import fontys.s3.carspacebackend.business.interfaces.IUserRepository;
import fontys.s3.carspacebackend.business.service.IBidService;
import fontys.s3.carspacebackend.domain.AccessToken;
import fontys.s3.carspacebackend.domain.Auction;
import fontys.s3.carspacebackend.domain.Bid;
import fontys.s3.carspacebackend.domain.User;
import fontys.s3.carspacebackend.exception.CannotPlaceBidException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class BidService implements IBidService {
    private IUserRepository userRepository;
    private IAuctionRepository auctionRepository;
    private IBidRepository bidRepository;

    private AccessToken requestAccessToken;


    @Override
    public Long createBid(Bid b, Long auctionId){
        concludeEndedAuctions();
        return placeBid(b, auctionId);
    }

    @Transactional
    public Long placeBid(Bid b, Long auctionId){
        User bidder = userRepository.findById(requestAccessToken.getUserId());
        Auction auction = auctionRepository.getAuctionById(auctionId);
        if(!auction.hasStarted()){
            throw new CannotPlaceBidException("Auction has not started yet");
        }
        else if(auction.hasEnded()){
            throw new CannotPlaceBidException("Auction has ended");
        }
        else if(auction.isOwner(bidder)){
            throw new CannotPlaceBidException("You cannot bid on your own auction");
        }
        else if(b.getAmount() < auction.getStartingPrice()){
            throw new CannotPlaceBidException("Bid is not above the minimum amount");
        }
        else if(b.getAmount() > auction.getBuyoutPrice()){
            throw new CannotPlaceBidException("Bid is above the buyout amount");
        }
        Bid highestBid = auction.getBids().stream().max(Comparator.comparingDouble(Bid::getAmount)).orElse(Bid.builder().amount(0.0).build());
        if(b.getAmount() <= highestBid.getAmount()){
            throw new CannotPlaceBidException("Bid is less than the highest bid");
        }

        return bidRepository.saveBid(b, auctionId, bidder.getId());
    }

    @Override
    public List<Bid> getAllBidsOnLiveAuctions(){
        return bidRepository.getBidsOnLiveAuctions();
    }

    @Transactional
    public void concludeEndedAuctions(){
        auctionRepository.endAuctions();
    }
}
