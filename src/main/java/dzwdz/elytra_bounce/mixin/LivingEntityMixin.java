package dzwdz.elytra_bounce.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    private int elytrabounce$timer = 0;

    private void elytrabounce$hook(LivingEntity entity, boolean val) {
        if (entity.getVelocity().y == 0) {
            elytrabounce$timer += 1;
            if (elytrabounce$timer > 4)
                ((EntityAccessor)entity).callSetFlag(7, val);
        } else {
            elytrabounce$timer = 0;
        }
    }

    @Redirect(method = "Lnet/minecraft/entity/LivingEntity;travel(Lnet/minecraft/util/math/Vec3d;)V",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"))
    public void travel(LivingEntity entity, int idx, boolean val) {
        elytrabounce$hook(entity, val);
    }
    @Redirect(method = "Lnet/minecraft/entity/LivingEntity;initAi()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"))
    public void initAi(LivingEntity entity, int idx, boolean val) {
        elytrabounce$hook(entity, val);
    }
}
