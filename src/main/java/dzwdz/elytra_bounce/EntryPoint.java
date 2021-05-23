package dzwdz.elytra_bounce;

import net.adriantodt.fallflyinglib.event.StopFallFlyingCallback;
import net.fabricmc.api.ModInitializer;

public class EntryPoint implements ModInitializer {
    @Override
    public void onInitialize() {
        StopFallFlyingCallback.EVENT.register((player, reason, cancel) -> {
            ElytraBounceLivingEntity ext = (ElytraBounceLivingEntity) player;
            if (reason == StopFallFlyingCallback.Reason.CONDITIONS_NOT_MET) {
                if (player.getVelocity().y == 0) {
                    if (ext.elytrabounce$getTimer() <= 4) {
                        cancel.run();
                    }
                    ext.elytrabounce$incrementTimer();
                } else {
                    ext.elytrabounce$resetTimer();
                }
            } else {
                ext.elytrabounce$resetTimer();
            }
        });
    }
}
