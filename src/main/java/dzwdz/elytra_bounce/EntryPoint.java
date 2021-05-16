package dzwdz.elytra_bounce;

import net.adriantodt.fallflyinglib.event.StopFallFlyingCallback;
import net.fabricmc.api.ModInitializer;

public class EntryPoint implements ModInitializer {
    @Override
    public void onInitialize() {
        StopFallFlyingCallback.EVENT.register((player, reason, cancel) -> {
            if (reason == StopFallFlyingCallback.Reason.CONDITIONS_NOT_MET) {
                ElytraBounceLivingEntity ext = (ElytraBounceLivingEntity) player;
                if (player.getVelocity().y == 0) {
                    if (ext.elytrabounce$getTimer() <= 1) {
                        cancel.run();
                    }
                    ext.elytrabounce$incrementTimer();
                } else {
                    ext.elytrabounce$resetTimer();
                }
            }
        });
    }
}
