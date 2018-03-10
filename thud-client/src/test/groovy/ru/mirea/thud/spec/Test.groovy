package ru.mirea.thud.spec

import akka.actor.ActorSystem
import akka.actor.Props
import ru.mirea.thud.client.constants.PlayerRole$
import ru.mirea.thud.client.model.GameUnit
import ru.mirea.thud.client.model.messages.CalculateMovementSchemeMessage
import ru.mirea.thud.client.service.PlayerService
import spock.lang.Shared
import spock.lang.Specification

import static akka.actor.ActorRef.*

class Test extends Specification {
    @Shared actorSystem = ActorSystem.create("TestSystem")

    def "А это тестик"() {
        setup:
        def actor = actorSystem.actorOf(Props.create(PlayerService))

        when:
        actor.tell(new CalculateMovementSchemeMessage(new GameUnit(null, PlayerRole$.MODULE$.DWARF(), null)), noSender())

        then:
        true
    }
}
