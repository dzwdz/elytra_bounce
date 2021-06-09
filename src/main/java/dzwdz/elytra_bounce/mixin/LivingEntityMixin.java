package dzwdz.elytra_bounce.mixin;

import dzwdz.elytra_bounce.ElytraBounceLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ElytraBounceLivingEntity {
    private int elytrabounce$timer = 0;
    private int elytrabounce$lastAge = 0;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public int elytrabounce$getTimer() {
        return elytrabounce$timer;
    }

    @Override
    public void elytrabounce$incrementTimer() {
        if (elytrabounce$lastAge != this.age) {
            elytrabounce$lastAge = this.age;
            elytrabounce$timer++;
        }
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
