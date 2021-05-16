package dzwdz.elytra_bounce.mixin;

import dzwdz.elytra_bounce.ElytraBounceLivingEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements ElytraBounceLivingEntity {
    private int elytrabounce$timer = 0;

    @Override
    public int elytrabounce$getTimer() {
        return elytrabounce$timer;
    }

    @Override
    public void elytrabounce$incrementTimer() {
        elytrabounce$timer++;
    }

    @Override
    public void elytrabounce$resetTimer() {
        elytrabounce$timer = 0;
    }

    @Redirect(
        method = "travel(Lnet/minecraft/util/math/Vec3d;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V")
    )
    public void travel(LivingEntity entity, int idx, boolean val) {
    }
}
